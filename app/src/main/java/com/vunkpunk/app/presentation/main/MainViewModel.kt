package com.vunkpunk.app.presentation.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.use_case.getCards.GetCardsBySearch
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCardsMiniUseCase: GetCardsMiniUseCase,
    private val getCardsBySearch: GetCardsBySearch,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(MainState())
    val state: State<MainState> = _state

    init {
        val text = savedStateHandle.get<String>(Constants.PARAM_SEARCH)
        if (text == null || text == "" || text == "{$PARAM_SEARCH}") {
            getCards()
        } else { searchCards(text) }
    }

    private fun getCards() {
        getCardsMiniUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MainState(cardsMini = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun searchCards(searchText: String) {
        getCardsBySearch(searchText).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MainState(cardsMini = result.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}