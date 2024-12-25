package com.vunkpunk.app.presentation.edit_profile

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants.USER_ID
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniFromUserUseCase
import com.vunkpunk.app.domain.use_case.patchUser.PatchUserUseCase
import com.vunkpunk.app.presentation.main.MainState
import com.vunkpunk.app.presentation.profile.ProfileState
import com.vunkpunk.domain.use_case.getUser.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val patchUserUseCase: PatchUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getCardsMiniFromUserUseCase: GetCardsMiniFromUserUseCase
) : ViewModel() {
    private val _user = mutableStateOf(ProfileState())
    val user: State<ProfileState> = _user

    private val _editProfileState = mutableStateOf(EditProfileState())
    val editProfileState: State<EditProfileState> = _editProfileState

    private val _imageState = mutableStateOf<Uri>(Uri.EMPTY)
    val imageState: State<Uri> = _imageState

    private val _publishedCards = mutableStateOf(MainState())
    val publishedCards: State<MainState> = _publishedCards

    private val _unpublishedCards = mutableStateOf(MainState())
    val unpublishedCards: State<MainState> = _unpublishedCards

    init {
        getUser(USER_ID)
        getPublishedCardsMiniFromUser(USER_ID)
        getUnpublishedCardsMiniFromUser(USER_ID)
    }

    sealed class UiEvent {
        data class AddImage(val uri: Uri) : UiEvent()
        data class PatchProfile(val context: Context) : UiEvent()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.AddImage -> {
                updateImages(event.uri)
            }

            is UiEvent.PatchProfile -> {
                setDefault()
                patchProfile(convertToBytes(imageState.value, event.context))
            }
        }
    }

    fun setDefault() {
        val state = _editProfileState.value
        val user = user.value.user
        state.first_name = user!!.first_name
        state.second_name = user.last_name
        state.description = user.description
        state.dormitory = user.dormitory
        state.faculty = user.faculty
        state.contact = user.contact ?: "Не указан"
    }

    fun editFirstName(newName: String) {
        _editProfileState.value = _editProfileState.value.copy(first_name = newName)
    }

    fun editSecondName(newName: String) {
        _editProfileState.value = _editProfileState.value.copy(second_name = newName)
    }

    fun editDormitory(newDormitory: String) {
        _editProfileState.value = _editProfileState.value.copy(dormitory = newDormitory)
    }

    fun editFaculty(newFaculty: String) {
        _editProfileState.value = _editProfileState.value.copy(faculty = newFaculty)
    }

    fun editContact(newContact: String) {
        _editProfileState.value = _editProfileState.value.copy(contact = newContact)
    }

    fun editDescription(newDescription: String) {
        _editProfileState.value = _editProfileState.value.copy(description = newDescription)
    }

    private fun convertToBytes(uri: Uri, context: Context): ByteArray {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val byteArrayOutputStream = ByteArrayOutputStream()

        inputStream?.use { input ->
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (input.read(buffer).also { bytesRead = it } != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead)
            }
        }

        return byteArrayOutputStream.toByteArray()
    }

    fun updateImages(image: Uri) {
        _imageState.value = image
    }

    private fun patchProfile(image: ByteArray) {
        val state = this.editProfileState.value
        patchUserUseCase(
            state.first_name,
            state.second_name,
            state.dormitory,
            state.faculty,
            state.contact,
            state.description,
            image
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("Post card", "Card created successfully")
                }

                is Resource.Error -> {

                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUser(userId: String) {
        getUserUseCase(userId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _user.value = ProfileState(user = result.data)
                }

                is Resource.Error -> {
                    _user.value = ProfileState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _user.value = ProfileState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPublishedCardsMiniFromUser(userId: String) {
        getCardsMiniFromUserUseCase(userId, true).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _publishedCards.value =
                        MainState(cardsMini = result.data ?: emptyList<CardMini>())
                }

                is Resource.Error -> {
                    _publishedCards.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _publishedCards.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUnpublishedCardsMiniFromUser(userId: String) {
        getCardsMiniFromUserUseCase(userId, false).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _unpublishedCards.value =
                        MainState(cardsMini = result.data ?: emptyList<CardMini>())
                }

                is Resource.Error -> {
                    _unpublishedCards.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _unpublishedCards.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}