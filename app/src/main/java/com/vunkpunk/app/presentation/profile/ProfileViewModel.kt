package com.vunkpunk.app.presentation.profile

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniFromUserUseCase
import com.vunkpunk.app.domain.use_case.getCards.GetCardsMiniUseCase
import com.vunkpunk.app.presentation.main.MainState
import com.vunkpunk.domain.use_case.getUser.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    private val getUserUseCase: GetUserUseCase,
    private val getCardsMiniFromUserUseCase: GetCardsMiniFromUserUseCase
) : ViewModel(){
    private val _user = mutableStateOf(ProfileState())
    val user: State<ProfileState> = _user

    private val _publishedCards = mutableStateOf(MainState())
    val publishedCards: State<MainState> = _publishedCards

    private val _unpublishedCards = mutableStateOf(MainState())
    val unpublishedCards: State<MainState> = _unpublishedCards

    init {
        val USER_ID = sharedPreferences.getInt("user_id", -1).toString() ?: ""
        getUser(USER_ID)
        getPublishedCardsMiniFromUser(USER_ID)
        getUnpublishedCardsMiniFromUser(USER_ID)
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

    private fun getPublishedCardsMiniFromUser(userId: String) {
        getCardsMiniFromUserUseCase(userId, true).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _publishedCards.value = MainState(cardsMini = result.data ?: emptyList<CardMini>())
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
                    _unpublishedCards.value = MainState(cardsMini = result.data ?: emptyList<CardMini>())
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