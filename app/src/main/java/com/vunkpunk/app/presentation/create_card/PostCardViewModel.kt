package com.vunkpunk.app.presentation.create_card

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.use_case.createCard.PostCardUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class PostCardViewModel @Inject constructor(
    private val postCardUseCase: PostCardUseCase,
) : ViewModel() {

    private val _postCardState = mutableStateOf<PostCardState>(
        PostCardState(
            "Введите название...",
            "Введите цену...",
            "Введите описание..."
        )
    )
    private val _imagesState = mutableStateOf<List<Uri>>(emptyList())

    val imagesState = _imagesState
    val postCardState: State<PostCardState> = _postCardState

    sealed class UiEvent {
        data class AddImages(val uris: List<Uri>) : UiEvent()
        data class PostCard(val context: Context) : UiEvent()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.AddImages -> {
                updateImages(event.uris)
            }

            is UiEvent.PostCard -> {
                createCard(postCardState.value.title,
                    postCardState.value.price,
                    postCardState.value.description,
                    imagesState.value.map { uri -> convertToBytes(uri, event.context) })
            }
        }
    }

    fun updateTitle(newTitle: String) {
        _postCardState.value = _postCardState.value.copy(title = newTitle)
    }

    fun updatePrice(newPrice: String) {
        _postCardState.value = _postCardState.value.copy(price = newPrice)
    }

    fun updateDescription(newDescription: String) {
        _postCardState.value = _postCardState.value.copy(description = newDescription)
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

        val a = byteArrayOutputStream.toByteArray()
        val bitmap = BitmapFactory.decodeByteArray(a, 0, a.size)
        return a
    }

    fun updateImages(images: List<Uri>) {
        _imagesState.value = images
    }

    fun createCard(title: String, price: String, description: String, images: List<ByteArray>) {
        postCardUseCase(title, price, description, images).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("Post card", "Card created successfully")
                }

                is Resource.Error -> {
                    _postCardState.value.error = result.message ?: "An unexpected error occured"
                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }
}