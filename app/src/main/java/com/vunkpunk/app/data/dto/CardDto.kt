package com.vunkpunk.app.data.dto

import com.vunkpunk.app.domain.model.CardDetail
import com.vunkpunk.app.domain.model.CardMini

data class CardDto(
    val description: String,
    val id: Int,
    val is_published: Boolean,
    val price: String,
    val rating: Double,
    val title: String,
    val user: Int
)


fun CardDto.toCardDetail(): CardDetail {
    return CardDetail(
        description = description,
        id = id,
        is_published = is_published,
        price = price,
        rating = rating,
        title = title,
        user = user,
    )
}

fun CardDto.toCardMini(): CardMini {
    return CardMini(
        id = id,
        is_published = is_published,
        price = price,
        rating = rating,
        title = title,
        user = user,
    )
}