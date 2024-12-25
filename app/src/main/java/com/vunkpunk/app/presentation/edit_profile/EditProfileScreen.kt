package com.vunkpunk.app.presentation.edit_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.edit_profile.components.EditHeader
import com.vunkpunk.app.presentation.edit_profile.components.EditProfileDescription
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation
import com.vunkpunk.app.presentation.login_system.Background
import com.vunkpunk.app.presentation.profile.components.Header
import com.vunkpunk.app.presentation.profile.components.HistoryDivider
import com.vunkpunk.app.presentation.profile.components.ListOfMiniCards
import com.vunkpunk.app.presentation.profile.components.ProfileDescription
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor


@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel(),
    openGallery: () -> Unit
) {
    Scaffold(
        topBar = {
            HeaderNavigation(navController)
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Content(navController = navController, viewModel, openGallery, innerPadding)
    }
}


@Composable
fun Content(
    navController: NavController,
    viewModel: EditProfileViewModel,
    openGallery: () -> Unit,
    padding: PaddingValues
) {
    val user = viewModel.user.value
    val publishedCards: List<CardMini> = viewModel.publishedCards.value.cardsMini
    val unpublishedCards: List<CardMini> = viewModel.unpublishedCards.value.cardsMini
    val context = LocalContext.current

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
                    EditHeader(navController, viewModel, openGallery)

                    Spacer(modifier = Modifier.height(20.dp))

                    EditProfileDescription(navController, viewModel)

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        viewModel.onEvent(EditProfileViewModel.UiEvent.PatchProfile(context))
                        navController.navigate(Screen.ProfileScreen.route)
                    }) {

                    }

                    Spacer(modifier = Modifier.height(50.dp))
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