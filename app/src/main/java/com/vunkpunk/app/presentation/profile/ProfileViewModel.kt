package com.vunkpunk.app.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants.USER_ID
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniUseCase
import com.vunkpunk.app.presentation.main.MainState
import com.vunkpunk.domain.use_case.getUser.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


fun getUserCards(cards: List<CardMini>?, userId: String): List<CardMini>{
    val output = mutableListOf<CardMini>()
    cards?.map { if(it.user.toString() == userId) output.add(it)}
    return output
}


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getCardsMiniUseCase: GetCardsMiniUseCase
) : ViewModel(){
    private val _user = mutableStateOf(ProfileState())
    val user: State<ProfileState> = _user

    private val _cards = mutableStateOf(MainState())
    val cards: State<MainState> = _cards

    init {
        getUser(USER_ID)
        getCardsFromUser(USER_ID)
    }

    private fun getUser(userId: String) {
        getUserUseCase(userId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _user.value = ProfileState( user = result.data)
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

    private fun getCardsFromUser(userId: String) {
        getCardsMiniUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _cards.value = MainState(cardsMini = getUserCards(result.data, userId))
                }

                is Resource.Error -> {
                    _cards.value = MainState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _cards.value = MainState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}