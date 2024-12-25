package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.data.dto.patch.PatchUserDto

interface UserApi {
    suspend fun getUserById(userId: String): UserDto
    suspend fun updateUserProfile(userProfile: PatchUserDto)
}