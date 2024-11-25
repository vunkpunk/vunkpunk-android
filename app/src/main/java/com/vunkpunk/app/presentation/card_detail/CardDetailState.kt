package com.vunkpunk.app.presentation.card_detail

import com.vunkpunk.app.domain.model.CardDetail

data class CardDetailState(
    val isLoading: Boolean = false,
    val cardDetail: CardDetail? = null,
    val error: String = ""
)

