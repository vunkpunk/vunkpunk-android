package com.vunkpunk.app.presentation.main

import com.vunkpunk.app.domain.model.CardMini

data class MainState(
    val isLoading: Boolean = false,
    val cardsMini: List<CardMini> = emptyList(),
    val error: String = ""
)