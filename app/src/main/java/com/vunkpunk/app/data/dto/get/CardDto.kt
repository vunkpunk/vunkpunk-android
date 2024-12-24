package com.vunkpunk.app.data.dto

import android.graphics.Bitmap
import com.vunkpunk.app.domain.model.CardDetail
import com.vunkpunk.app.domain.model.CardMini


data class CardDto(
    val description: String,
    val dormitory: String,
    val id: Int,
    val is_published: Boolean,
    val uploaded_images: List<PhotoDto>,
    val price: String,
    val rating: Double,
    val title: String,
    val user: Int,
    val contact: String
)


fun CardDto.toCardDetail(photos: List<Bitmap>): CardDetail {
    return CardDetail(
        description = description,
        dormitory = dormitory,
        id = id,
        is_published = is_published,
        price = price,
        rating = rating,
        title = title,
        user = user,
        contact = contact,
        photos = photos
    )
}

fun CardDto.toCardMini(photo: Bitmap): CardMini {
    return CardMini(
        id = id,
        is_published = is_published,
        dormitory = dormitory,
        price = price,
        rating = rating,
        title = title,
        user = user,
        photo = photo
    )
}