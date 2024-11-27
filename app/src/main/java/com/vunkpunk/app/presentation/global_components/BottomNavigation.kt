package com.vunkpunk.app.presentation.global_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor

@Composable
fun BottomNavigation(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Row {
            // MainScreen
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(GeneralBackgroundColor)
                .clickable { navController.navigate(Screen.MainScreen.route) })
            {
                Image(
                    painter = painterResource(R.drawable.baseline_home_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            VerticalDivider(color = Color.Black)
            // About
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(GeneralBackgroundColor)
                .clickable { }) {
                Image(
                    painter = painterResource(R.drawable.baseline_info_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            VerticalDivider(color = Color.Black)
            // CreateCard
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(GeneralBackgroundColor)
                .clickable { }) {
                Image(
                    painter = painterResource(R.drawable.baseline_add_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            VerticalDivider(color = Color.Black)
            // Settings
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.2f)
                    .background(GeneralBackgroundColor)
            ){
                Image(
                    painter = painterResource(R.drawable.baseline_settings_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            VerticalDivider(color = Color.Black)
            // Profile
            Box(modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(GeneralBackgroundColor)
                .clickable { navController.navigate(Screen.ProfileScreen.route) }){
                Image(
                    painter = painterResource(R.drawable.baseline_person_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}