package com.vunkpunk.app.presentation.card_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.use_case.getCards.GetCardDetailUseCase
import com.vunkpunk.app.presentation.main.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val getCardDetailUseCase: GetCardDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CardDetailState())
    val state: State<CardDetailState> = _state

    init {
//        savedStateHandle[Constants.PARAM_CARD_ID] = "1"
        savedStateHandle.get<String>(Constants.PARAM_CARD_ID)?.let { cardId ->
            getCard(cardId)
        }
    }

    private fun getCard(cardId: String) {
        getCardDetailUseCase(cardId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CardDetailState(cardDetail = result.data)
                }

                is Resource.Error -> {
                    _state.value = CardDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CardDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}