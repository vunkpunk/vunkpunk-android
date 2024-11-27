package com.vunkpunk.app.presentation.global_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vunkpunk.app.presentation.Screen

@Composable
fun BottomNavigation(navController: NavController) {
    
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)) {
        Row {
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(Color.Red)
                .clickable { navController.navigate(Screen.MainScreen.route) })
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(Color.Black)
                .clickable { navController.navigate(Screen.CardDetailScreen.route) })
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(Color.Red)
                .clickable { navController.navigate(Screen.ProfileScreen.route) })
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(Color.Black))
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(Color.Red))
        }
    }
}