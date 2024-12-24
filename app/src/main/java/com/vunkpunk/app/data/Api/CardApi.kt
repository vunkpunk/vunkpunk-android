package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.CardDto
import com.vunkpunk.app.data.dto.PostCardDto

interface CardApi {
    suspend fun getCards(): List<CardDto>
    suspend fun getCardById(cardId: String): CardDto
    suspend fun getCardsByUserId(userId: String, published: Boolean = true): List<CardDto>
    suspend fun getCardsBySearch(paramSearch: String): List<CardDto>
    suspend fun postCard(postCardDto: PostCardDto, images: List<ByteArray>)
}