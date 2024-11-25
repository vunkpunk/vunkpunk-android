package com.vunkpunk.app.presentation.profile

import com.vunkpunk.app.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)