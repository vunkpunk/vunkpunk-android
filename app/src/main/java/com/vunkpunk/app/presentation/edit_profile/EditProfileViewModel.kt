package com.vunkpunk.app.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants.USER_ID
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniFromUserUseCase
import com.vunkpunk.app.domain.use_case.postUser.PostUserUseCase
import com.vunkpunk.app.presentation.main.MainState
import com.vunkpunk.app.presentation.profile.ProfileState
import com.vunkpunk.domain.use_case.getUser.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val postUserUseCase: PostUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getCardsMiniFromUserUseCase: GetCardsMiniFromUserUseCase
) : ViewModel() {
    private val _user = mutableStateOf(ProfileState())
    val user: State<ProfileState> = _user

    private val _editProfileState = mutableStateOf(EditProfileState())
    val editProfileState: State<EditProfileState> = _editProfileState

    private val _publishedCards = mutableStateOf(MainState())
    val publishedCards: State<MainState> = _publishedCards

    private val _unpublishedCards = mutableStateOf(MainState())
    val unpublishedCards: State<MainState> = _unpublishedCards

    init {
        getUser(USER_ID)
        getPublishedCardsMiniFromUser(USER_ID)
        getUnpublishedCardsMiniFromUser(USER_ID)
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

    private fun postProfile() {
        val state = this.editProfileState.value
        postUserUseCase(
            state.first_name,
            state.second_name,
            state.dormitory,
            state.faculty,
            state.contact,
            state.description
        )
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