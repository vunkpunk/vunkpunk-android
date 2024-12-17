package com.vunkpunk.app.domain.repository

import com.vunkpunk.app.data.dto.CardDto
import com.vunkpunk.app.data.dto.PostCardDto

interface CardRepository {
    suspend fun getCards(): List<CardDto>
    suspend fun getCardById(cardId: String): CardDto
    suspend fun getCardsByUserId(userId: String): List<CardDto>
    suspend fun getCardsBySearch(paramSearch: String): List<CardDto>
    suspend fun postCard(postCardDto: PostCardDto)
}