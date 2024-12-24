package com.vunkpunk.app.data.dto.response

data class LogInUserDtoResponseSuccessDto(
    val auth_token: String = "",
)

data class LogInUserDtoResponseFailDto(
    val non_field_errors: List<String> = listOf("unexpected error"),
)