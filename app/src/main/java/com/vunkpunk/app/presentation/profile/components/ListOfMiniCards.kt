package com.vunkpunk.app.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.global_components.CardMini

@Composable
fun ListOfMiniCards(cards: List<CardMini>, navController: NavController) {

    val _height = cards.size * 300

    LazyVerticalStaggeredGrid(
        modifier = Modifier.height(_height.dp),
        columns = StaggeredGridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalItemSpacing = 20.dp,
    ) {
        items(cards) { card ->
            CardMini(
                card = card,
                navController = navController
            )
        }
    }
}