package com.vunkpunk.app.data.dto.response

data class SignUpUserResponseSuccessDto(
    val username: String? = null,
    val email: String? = null
)

data class SignUpUserResponseFailDto(
    val username: List<String>? = null,
    val email: List<String>? = null
)