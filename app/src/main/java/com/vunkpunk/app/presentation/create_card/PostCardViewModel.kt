package com.vunkpunk.app.presentation.create_card

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
import javax.inject.Inject

@HiltViewModel
class PostCardViewModel @Inject constructor(
    private val postCardUseCase: PostCardUseCase,
) : ViewModel(){

    private val _postCardState = mutableStateOf<PostCardState>(PostCardState(
        "Введите название...",
        "Введите цену...",
        "Введите описание..."
    ))
    val postCardState: State<PostCardState> = _postCardState

    sealed class UiEvent {
        object PostCard: UiEvent()
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.PostCard -> {createCard(postCardState.value.title,
                postCardState.value.price,
                postCardState.value.description)
                Log.d("PostCardViewmodel", (event == UiEvent.PostCard).toString())}

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

    private fun createCard(title: String, price: String, description: String) {
        postCardUseCase(title, price, description).onEach { result ->
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