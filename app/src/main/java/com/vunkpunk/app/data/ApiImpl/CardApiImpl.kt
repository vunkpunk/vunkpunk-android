package com.vunkpunk.app.data.ApiImpl

import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.dto.get.CardDto
import com.vunkpunk.app.data.dto.post.PostCardDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class CardApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson,
    private val token: String
) : CardApi {

    override suspend fun getCards(): List<CardDto> {
        val resp = client.get("$BASE_URL/sales") {
        }
        val cards = gson.fromJson(resp.bodyAsText(), Array<CardDto>::class.java).asList()
        return cards
    }

    override suspend fun getCardById(cardId: String): CardDto {
        val resp = client.get("$BASE_URL/sales/$cardId") {
            header("Authorization", "Token ${token}")
        }
        val card = gson.fromJson(resp.bodyAsText(), CardDto::class.java)
        return card
    }

    override suspend fun getCardsByUserId(userId: String): List<CardDto> {
        val resp = client.get("$BASE_URL/sales/?user_id=$userId") {
            header("Authorization", "Token ${token}")
        }
        val cards = gson.fromJson(resp.bodyAsText(), Array<CardDto>::class.java).asList()
        return cards
    }

    override suspend fun getCardsBySearch(paramSearch: String): List<CardDto> {
        val resp = client.get("$BASE_URL/sales/?search=$paramSearch") {
            header("Authorization", "Token ${token}")
        }
        val cards = gson.fromJson(resp.bodyAsText(), Array<CardDto>::class.java).asList()
        return cards
    }

    override suspend fun postCard(postCardDto: PostCardDto) {
        val jsonBody = gson.toJson(postCardDto)
        val resp = client.post("$BASE_URL/sales/") {
            contentType(ContentType.Application.Json)
            header("Authorization", "Token $token")
            header("Content-Type", "application/json")
            setBody(jsonBody)
        }
    }
}