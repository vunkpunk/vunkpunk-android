package com.vunkpunk.app.domain.repository

import android.graphics.Bitmap

interface PhotoRepository {
    suspend fun getCardPhotoById(photoId: String) : Bitmap
}