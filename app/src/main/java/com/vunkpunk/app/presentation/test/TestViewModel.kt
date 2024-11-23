package com.vunkpunk.app.presentation.test

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vunkpunk.app.common.Constants
import com.vunkpunk.app.common.Resource
import com.vunkpunk.app.presentation.test.TestState
import com.vunkpunk.domain.use_case.getUser.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TestState())
    val state: State<TestState> = _state

    init {
        savedStateHandle[Constants.PARAM_USER_ID] = "1"
        savedStateHandle.get<String>(Constants.PARAM_USER_ID)?.let { userId ->
            getUser(userId)
        }
        Log.d("TestViewModel", "end of init part")
    }

    private fun getUser(userId: String) {
        Log.d("TestViewModel", "start of getUser func with userId=$userId")

        getUserUseCase(userId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = TestState(user = result.data)
                }
                is Resource.Error -> {
                    _state.value = TestState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = TestState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
        Log.d("TestViewModel", "getUserUseCase was called")
    }
}