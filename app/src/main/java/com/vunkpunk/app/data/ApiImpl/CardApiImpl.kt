package com.vunkpunk.app.data.ApiImpl

import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.data.Api.CardApi
import com.vunkpunk.app.data.dto.get.CardDto
import com.vunkpunk.app.data.dto.post.PostCardDto
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import javax.inject.Inject

class CardApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson,
    private val token: String
) : CardApi {

    override suspend fun getCards(): List<CardDto> {
        val resp = client.get("$BASE_URL/sales") {
            header("Authorization", "Token ${token}")
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

    override suspend fun getCardsByUserId(userId: String, published: Boolean): List<CardDto> {
        val resp = client.get("$BASE_URL/sales/?all=$published&user_id=$userId") {
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

    override suspend fun postCard(postCardDto: PostCardDto, images: List<ByteArray>) {
        // TODO: Убрать хардкод
        val formData = formData {
            append("title", postCardDto.title)
            append("description", postCardDto.description)
            append("price", postCardDto.price)
            append("is_published", postCardDto.is_published)

            images.forEachIndexed { index, file ->
                append("images", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "name=\"images\"; filename=image$index.jpg")
                })
            }
        }

        val resp = client.submitFormWithBinaryData("$BASE_URL/sales/", formData) {
            header("Authorization", "Token $token")
            header("Content-Type", "multipart/form-data")
        }
    }
}