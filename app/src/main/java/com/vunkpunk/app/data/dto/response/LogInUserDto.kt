package com.vunkpunk.app.data.dto.response

data class LogInUserDtoResponseDto(
    val auth_token: String = "",
    val id: Int = 0,
    val non_field_errors: List<String> = listOf(""),
)

data class LogInUserDtoResponseFailDto(
    val non_field_errors: List<String> = listOf("unexpected error"),
)