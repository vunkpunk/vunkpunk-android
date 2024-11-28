package com.vunkpunk.app.presentation.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vunkpunk.app.presentation.card_detail.Content
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.global_components.HeadNavigation.HeaderNavigation

@Composable
fun AboutScreen(navController: NavController){

    Scaffold(
        topBar = {
            HeaderNavigation(navController)
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Content(padding = innerPadding)
    }
}

@Composable
fun Content(padding: PaddingValues){
    Box(modifier = Modifier.padding(padding)) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id es")
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "САМЫЕ КРУТЫЕ РАЗРАБОТЧИКИ: @iraedeus, @RomanyukArtem, @kubikoid")
        }
    }
}