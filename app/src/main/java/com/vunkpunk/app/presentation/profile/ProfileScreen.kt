package com.vunkpunk.app.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.domain.use_case.logOutUser.LogOutUserUseCase
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.CardMini
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation
import com.vunkpunk.app.presentation.login_system.Background
import com.vunkpunk.app.presentation.profile.components.Header
import com.vunkpunk.app.presentation.profile.components.HistoryDivider
import com.vunkpunk.app.presentation.profile.components.ListOfMiniCards
import com.vunkpunk.app.presentation.profile.components.ProfileDescription
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            HeaderNavigation(navController)
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
    val publishedCards: List<CardMini> = viewModel.publishedCards.value.cardsMini
    val unpublishedCards: List<CardMini> = viewModel.unpublishedCards.value.cardsMini

    Background()
    if (user.user == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(user.error, modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
        {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            modifier = Modifier,
                            onClick = { viewModel.onEvent(ProfileViewModel.UiEvent.Logout)
                            navController.navigate(Screen.WelcomeScreen.route)}) {
                            Text(
                                text = "Logout", fontSize = 15.sp, fontFamily = robotoMonoFamily,
                                fontWeight = FontWeight.Normal, color = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Header(user.user!!)

                    Spacer(modifier = Modifier.height(20.dp))

                    ProfileDescription(user.user!!, navController)

                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    ListOfMiniCards(cards = publishedCards, navController = navController)
                }

                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    HistoryDivider()
                    Spacer(modifier = Modifier.height(20.dp))
                }

                item {
                    ListOfMiniCards(cards = unpublishedCards, navController = navController)
                }
            }
        }
    }
}