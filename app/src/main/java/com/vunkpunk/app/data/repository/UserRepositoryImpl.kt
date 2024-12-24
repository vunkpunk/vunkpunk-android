package com.vunkpunk.app.data.repository

import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): UserDto {
        return api.getUserById(userId)
    }

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