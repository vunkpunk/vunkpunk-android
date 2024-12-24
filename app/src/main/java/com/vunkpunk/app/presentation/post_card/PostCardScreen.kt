package com.vunkpunk.app.presentation.post_card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.decode.ImageSource
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation
import com.vunkpunk.app.presentation.theme.GeneralColor
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor
import com.vunkpunk.app.presentation.theme.MinorTextColor

@Composable
fun PostCardScreen(
    navController: NavController,
    viewModel: PostCardViewModel = hiltViewModel(),
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
        Content(
            navController = navController,
            viewModel = viewModel,
            openGallery,
            padding = innerPadding
        )
    }

}

@Composable
fun Content(
    navController: NavController,
    viewModel: PostCardViewModel,
    openGallery: () -> Unit,
    padding: PaddingValues
) {

    val context = LocalContext.current
    val postCardState by viewModel.postCardState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .background(color = MinorBackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Создать объявление", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Название объявления", fontSize = 16.sp)
        TextField(value = postCardState.title,
            placeholder = { Text(text = "Введите название...", color = MinorTextColor) },
            onValueChange = { viewModel.updateTitle(it) })
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Укажите цену", fontSize = 16.sp)
        TextField(
            value = postCardState.price,
            placeholder = { Text(text = "Введите цену...", color = MinorTextColor) },
            onValueChange = { viewModel.updatePrice(it) })
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Добавьте описание", fontSize = 16.sp)
        TextField(
            value = postCardState.description,
            placeholder = { Text(text = "Введите описание...", color = MinorTextColor) },
            onValueChange = { viewModel.updateDescription(it) })
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Добавить фото", fontSize = 16.sp)
        Box(
            modifier = Modifier
                .size(height = 160.dp, width = 320.dp)
                .background(color = GeneralColor)
                .clickable { openGallery() }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            viewModel.onEvent(PostCardViewModel.UiEvent.PostCard(context))
            viewModel.resetState()
            navController.navigate(Screen.MainScreen.route +  "/{$PARAM_SEARCH}")
        }) {
        }
    }
}