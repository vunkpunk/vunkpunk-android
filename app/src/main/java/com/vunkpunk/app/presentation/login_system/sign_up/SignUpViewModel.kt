package com.vunkpunk.app.presentation.login_system.sign_up

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.domain.use_case.signUpUser.SignUpUserUseCase
import com.vunkpunk.app.presentation.login_system.LoginSystemEvent
import com.vunkpunk.app.presentation.login_system.LoginSystemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(LoginSystemState())
    val state: State<LoginSystemState> = _state

//    init {
//        val text = savedStateHandle.get<String>(Constants.PARAM_SEARCH)
//    }

    fun onEvent(event: LoginSystemEvent) {
        when (event) {
            is LoginSystemEvent.SignUp -> {
                signUp(
                    _state.value.userName,
                    _state.value.password,
                    _state.value.email,
                )
            }else -> {
                val eventString = event.toString()
                Log.d("SignUpViewModel", "Unexpected event: $eventString")
        }
        }
    }

    private fun signUp(userName: String, password: String, email: String) {
        val signUpUser = SignUpUserDto(email, userName, password)
        signUpUserUseCase(signUpUser).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    state.value.isAuth.value = true
                }

                is Resource.Error -> {
                    _state.value.error.value = result.message ?: "An unexpected error occured"
                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)

    }
}