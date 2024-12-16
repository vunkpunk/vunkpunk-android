package com.vunkpunk.app.presentation.test

import com.vunkpunk.app.domain.model.User

data class TestState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)