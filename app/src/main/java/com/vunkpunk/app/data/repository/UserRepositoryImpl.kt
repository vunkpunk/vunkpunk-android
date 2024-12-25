package com.vunkpunk.app.data.repository

import com.vunkpunk.app.data.Api.LoginUserApi
import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.data.dto.patch.PatchUserDto
import com.vunkpunk.app.data.dto.post.LogInUserDto
import com.vunkpunk.app.data.dto.post.SendCodeDto
import com.vunkpunk.app.data.dto.post.SignUpUserDto
import com.vunkpunk.app.domain.repository.LoginUserRepository
import com.vunkpunk.app.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUserById(userId: String): UserDto {
        return api.getUserById(userId)
    }

    override suspend fun updateUserProfile(userProfile: PatchUserDto) {
        api.updateUserProfile(userProfile)
    }
}