package com.vunkpunk.app.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardsMiniUseCase: GetCardsMiniUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CardsState())
    val state: State<CardsState> = _state

    init {
        getCards()
    }

    private fun getCards() {
        getCardsMiniUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CardsState(cardsMini = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = CardsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CardsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}