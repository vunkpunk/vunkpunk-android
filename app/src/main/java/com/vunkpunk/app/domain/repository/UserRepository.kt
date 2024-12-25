package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.data.dto.patch.PatchUserDto

interface UserRepository {
    suspend fun getUserById(userId: String) : UserDto
    suspend fun updateUserProfile(userProfile: PatchUserDto)
}