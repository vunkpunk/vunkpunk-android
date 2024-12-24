package com.vunkpunk.app.presentation.login_system

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginSystemState(
    var password: String = "",
    var email: String = "",
    var userName: String = "",
    var code: String = "",
    var isAuth: MutableState<Boolean> = mutableStateOf<Boolean>(false),
    var error: MutableState<String> = mutableStateOf<String>("")
)