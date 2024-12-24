package com.vunkpunk.app.data.Api

import com.vunkpunk.app.data.dto.get.CardDto
import com.vunkpunk.app.data.dto.post.PostCardDto

interface CardApi {
    suspend fun getCards(): List<CardDto>
    suspend fun getCardById(cardId: String): CardDto
    suspend fun getCardsByUserId(userId: String): List<CardDto>
    suspend fun getCardsBySearch(searchText: String): List<CardDto>
    suspend fun postCard(postCardDto: PostCardDto)
}