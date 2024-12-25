package com.vunkpunk.app.data.ApiImpl

import com.google.gson.Gson
import com.vunkpunk.app.common.Constants.BASE_URL
import com.vunkpunk.app.data.Api.UserApi
import com.vunkpunk.app.data.dto.get.UserDto
import com.vunkpunk.app.data.dto.patch.PatchUserDto
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitForm
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.parametersOf
import java.lang.reflect.Parameter
import javax.inject.Inject

class UserApiImpl @Inject constructor(
    private val client: HttpClient,
    private val gson: Gson,
    private val token: String,
    private val userId: Int
) : UserApi {
    override suspend fun getUserById(userId: String): UserDto {
        val resp = client.get("$BASE_URL/user/$userId/") {
            header("Authorization", "Token $token")
        }
        val user = gson.fromJson(resp.bodyAsText(), UserDto::class.java)
        return user
    }

    override suspend fun updateUserProfile(userProfile: PatchUserDto, image: ByteArray) {
        // TODO: Убрать хардкод
        val formData = formData {
            append("first_name", userProfile.first_name)
            append("last_name", userProfile.second_name)
            append("dormitory", userProfile.dormitory)
            append("faculty", userProfile.faculty)
            append("contact", userProfile.contact)
            append("description", userProfile.description)
            append("photo", image, Headers.build {
                append(HttpHeaders.ContentDisposition, "name=\"photo\"; filename=photo.jpg")
            })
        }

        val resp = client.submitFormWithBinaryData(
            "$BASE_URL/user/$userId", formData
        ) {
            header("Authorization", "Token $token")
            header("Content-Type", "multipart/form-data")
            method = HttpMethod.Patch
        }
    }
}