package com.vunkpunk.app.data.repository

import android.util.Log
import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.dto.UserDto
import com.vunkpunk.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): UserDto {
        return api.getUserById(userId)
    }

}