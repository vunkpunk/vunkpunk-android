package com.vunkpunk.app.data.dto.post

data class SignUpUserDto(
    val email: String,
    val username: String,
    val password: String
)

data class SendCodeDto(
    val email: String,
    val activation_code: String,
)