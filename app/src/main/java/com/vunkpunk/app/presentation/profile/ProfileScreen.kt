package com.vunkpunk.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.CardMini
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation
import com.vunkpunk.app.presentation.profile.components.Header
import com.vunkpunk.app.presentation.profile.components.HistoryDivider
import com.vunkpunk.app.presentation.profile.components.ProfileDescription
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            HeaderNavigation()
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Content(navController = navController, viewModel = viewModel, padding = innerPadding)
    }

}

@Composable
fun Content(navController: NavController, viewModel: ProfileViewModel, padding: PaddingValues) {
    val user = viewModel.user.value
    val cards: List<CardMini> = viewModel.cards.value.cardsMini

    if(user.user == null){
        Box(modifier = Modifier.fillMaxSize()) {
            Text(user.error, modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MinorBackgroundColor)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Header(user.user!!)

                    Spacer(modifier = Modifier.height(20.dp))

                    ProfileDescription(user.user!!)

                    Spacer(modifier = Modifier.height(50.dp))
                }

                item {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.height(1000.dp),
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

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    HistoryDivider()
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.height(1000.dp),
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
            }
        }
    }
}