package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.data.dto.get.UserDto

interface UserRepository {
    suspend fun getUserById(userId: String): UserDto
    suspend fun signUpUser(signUpUser: SignUpUserDto): String
    suspend fun logInUser(logInUser: LogInUserDto): String
    suspend fun sendCode(userCode: SendCodeDto): Boolean
}