package com.vunkpunk.app.presentation.login_system

sealed class LoginSystemEvent {
    object SignUp: LoginSystemEvent()
    object LogIn: LoginSystemEvent()
    object SendCode: LoginSystemEvent()
}