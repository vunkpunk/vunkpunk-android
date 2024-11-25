package com.vunkpunk.app.presentation.profile

import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.model.User

data class ProfileState(
    val isLoading: Boolean = false,
    var user: User? = null,
    var userMiniCards: List<CardMini> = emptyList<CardMini>(),
    val error: String = ""
)