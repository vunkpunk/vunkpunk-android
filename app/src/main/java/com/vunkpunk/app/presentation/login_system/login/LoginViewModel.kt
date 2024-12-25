package com.vunkpunk.app.presentation.login_system.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.domain.use_case.logInUser.LogInUserUseCase
import com.vunkpunk.app.presentation.login_system.LoginSystemEvent
import com.vunkpunk.app.presentation.login_system.LoginSystemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUserUseCase: LogInUserUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(LoginSystemState())
    val state: State<LoginSystemState> = _state


    fun onEvent(event: LoginSystemEvent) {
        when (event) {
            is LoginSystemEvent.LogIn -> {
                logIn(
                    _state.value.email,
                    _state.value.password,
                )
            }

            else -> {
                val eventString = event.toString()
                Log.d("LoginViewModel", "Unexpected event: $eventString")
            }
        }
    }

    private fun logIn(email: String, password: String) {
        val logInUser = LogInUserDto(email, password)
        logInUserUseCase(logInUser).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value.isAuth.value = true
                }
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    val r = result.message
                    Log.d("LoginViewModel", "error: $r")
                    _state.value.error.value = result.message ?: "An unexpected error occurred"
                }
            }
        }.launchIn(viewModelScope)

    }
}
