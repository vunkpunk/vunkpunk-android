package com.vunkpunk.app.data.repository

import com.vunkpunk.app.data.remote.UserApi
import com.vunkpunk.app.data.remote.dto.UserDto
import com.vunkpunk.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): UserDto {
        return api.getUserById(userId)
    }

}