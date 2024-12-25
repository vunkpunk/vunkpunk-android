package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.data.dto.response.LogInUserDtoResponseDto

interface LoginUserApi {
    suspend fun signUpUser(signUpUser: SignUpUserDto): String
    suspend fun logInUser(logInUser: LogInUserDto): LogInUserDtoResponseDto
    suspend fun sendCode(userCode: SendCodeDto): Boolean
}