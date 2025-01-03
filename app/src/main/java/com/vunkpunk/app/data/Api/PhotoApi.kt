package com.vunkpunk.app.data.Api

interface PhotoApi {
    suspend fun getCardPhotoById(photoId: String): ByteArray
    suspend fun getUserPhotoById(userId: String): ByteArray
}