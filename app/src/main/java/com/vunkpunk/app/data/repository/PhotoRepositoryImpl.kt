package com.vunkpunk.app.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.Api.PhotoApi
import com.vunkpunk.app.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: PhotoApi
) : PhotoRepository {

    override suspend fun getCardPhotoById(photoId: String) : Bitmap {
        val byteArray = api.getCardPhotoById(photoId)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    override suspend fun getUserPhotoById(userId: String): Bitmap {
        val byteArray = api.getUserPhotoById(userId)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}