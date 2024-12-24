package com.vunkpunk.app.data.repository

import com.vunkpunk.app.data.Api.LoginUserApi
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.domain.repository.LoginUserRepository
import javax.inject.Inject

class LoginUserRepositoryImpl @Inject constructor(
    private val api: LoginUserApi
) : LoginUserRepository {

    override suspend fun signUpUser(signUpUser: SignUpUserDto): String {
        return api.signUpUser(signUpUser)
    }

    override suspend fun logInUser(logInUser: LogInUserDto): String {
        return api.logInUser(logInUser)
    }

    override suspend fun sendCode(userCode: SendCodeDto): Boolean {
        return api.sendCode(userCode)
    }

}