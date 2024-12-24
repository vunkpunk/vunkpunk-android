package com.vunkpunk.app.data.repository

import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.dto.get.CardDto
import com.vunkpunk.app.data.dto.post.PostCardDto
import com.vunkpunk.app.domain.repository.CardRepository
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi
) : CardRepository {

    override suspend fun getCards(): List<CardDto> {
        return api.getCards()
    }

    override suspend fun getCardById(cardId: String): CardDto {
        return api.getCardById(cardId)
    }

    override suspend fun getCardsByUserId(userId: String, published: Boolean): List<CardDto> {
        return api.getCardsByUserId(userId, published)
    }

    override suspend fun getCardsBySearch(searchText: String): List<CardDto> {
        return api.getCardsBySearch(searchText)
    }

    override suspend fun postCard(postCardDto: PostCardDto, images: List<ByteArray>): Unit {
        api.postCard(postCardDto, images)
    }
}