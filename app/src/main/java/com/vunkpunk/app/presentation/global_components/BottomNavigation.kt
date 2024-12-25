package com.vunkpunk.app.presentation.global_components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.Pink40
import com.vunkpunk.app.presentation.theme.Pink80
import com.vunkpunk.app.presentation.theme.Purple80
import com.vunkpunk.app.presentation.theme.PurpleGrey40

@Composable
fun BottomNavigation(navController: NavController) {

    val context = LocalContext.current
    val userId =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE).getInt("user_id", -1)

    Column(modifier = Modifier.padding(bottom = 20.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
                    .background(Color.White)
                    .align(AbsoluteAlignment.BottomRight)

            )

            // MAIN
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.BottomStart)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Pink80,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                        .align(Alignment.Center)
                        .graphicsLayer(
                            shadowElevation = 100F, // Глубина тени
                            shape = androidx.compose.foundation.shape.CircleShape, // Форма тени
                            clip = true, // Обрезка по форме
                        )
                        .clickable { navController.navigate(Screen.MainScreen.route) }

                ) {
                    Image(
                        painter = painterResource(R.drawable.home),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            // PLUS
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .background(Purple80, shape = androidx.compose.foundation.shape.CircleShape)
                    .align(Alignment.TopCenter)
                    .clickable { navController.navigate(Screen.PostCardScreen.route) }
                    .graphicsLayer(
                        shadowElevation = 150F, // Глубина тени
                        shape = androidx.compose.foundation.shape.CircleShape, // Форма тени
                        clip = true, // Обрезка по форме
                        translationY = 25F // Смещение тени вниз
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.plus),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                        .graphicsLayer(
                            shadowElevation = 75F, // Глубина тени
                            shape = androidx.compose.foundation.shape.CircleShape, // Форма тени
                            clip = true, // Обрезка по форме
                            translationY = -30F // Смещение тени вниз
                        )
                )
            }

            // USER
            Box(
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            Pink80,
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                        .align(Alignment.Center)
                        .graphicsLayer(
                            shadowElevation = 100F, // Глубина тени
                            shape = androidx.compose.foundation.shape.CircleShape, // Форма тени
                            clip = true, // Обрезка по форме
                        )
                        .clickable {
                            navController.navigate(
                                Screen.ProfileScreen.route
                            )
                        }

                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(30.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}