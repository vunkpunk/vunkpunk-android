package com.vunkpunk.app.presentation.login_system.code

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.domain.use_case.sendCode.SendCodeUseCase
import com.vunkpunk.app.presentation.login_system.LoginSystemEvent
import com.vunkpunk.app.presentation.login_system.LoginSystemState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class CodeViewModel @Inject constructor(
    private val sendCodeUseCase: SendCodeUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _state = mutableStateOf(LoginSystemState())
    val state: State<LoginSystemState> = _state
    init {
        state.value.email =  savedStateHandle.get<String>(Constants.PARAM_EMAIL) ?: ""
    }
    fun onEvent(event: LoginSystemEvent) {
        when (event) {
            is LoginSystemEvent.SendCode -> {
                sendCode(
                    _state.value.email,
                    _state.value.code,
                )
            }else -> {
            val eventString = event.toString()
            Log.d("CodeViewModel", "Unexpected event: $eventString")
        }
        }
    }
    private fun sendCode(email: String, code: String){
        val userCode = SendCodeDto(email, code)
        sendCodeUseCase(userCode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.d("CodeViewModel", state.value.email)
                    _state.value.isAuth.value = true
                }

                is Resource.Error -> {
                    val r = result.message
                    Log.d("CodeViewModel", r.toString())
                    _state.value.error.value = result.message ?: "An unexpected error occured"
                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)

    }
}