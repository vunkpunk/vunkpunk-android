package com.vunkpunk.app.presentation.main


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.CardMini
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation
import com.vunkpunk.app.presentation.login_system.Background
import com.vunkpunk.app.presentation.profile.ProfileViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            HeaderNavigation(navController)
        },
        bottomBar = {
            BottomNavigation(navController)
        },
        content = { innerPadding ->
            Content(navController = navController, viewModel = viewModel, padding = innerPadding)
        },
    )

}

@Composable
fun Content(navController: NavController, viewModel: MainViewModel, padding: PaddingValues) {
    val state = viewModel.state.value
    val cards: List<CardMini> = state.cardsMini
    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 0.dp, bottom = padding.calculateBottomPadding() - 15.dp)
    ) {
        Background()
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalItemSpacing = 16.dp,
        ) {
            items(cards) { card -> CardMini(card = card, navController = navController) }
        }
    }
}