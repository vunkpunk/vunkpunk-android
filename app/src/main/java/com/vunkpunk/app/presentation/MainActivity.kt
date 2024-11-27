package com.vunkpunk.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vunkpunk.app.common.Constants.PARAM_CARD_ID
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.common.Constants.PARAM_USER_ID
import com.vunkpunk.app.presentation.card_detail.CardDetailScreen
import com.vunkpunk.app.presentation.main.MainScreen
import com.vunkpunk.app.presentation.profile.ProfileScreen
import com.vunkpunk.app.presentation.test.TestScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route + "/{$PARAM_SEARCH}"
                ) {
                    composable(
                        route = Screen.MainScreen.route + "/{$PARAM_SEARCH}"
                    ) {
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.ProfileScreen.route
                    ) {
                        ProfileScreen(navController)
                    }
                    composable(
                        route = Screen.CardDetailScreen.route + "/{$PARAM_CARD_ID}",
                    ) {
                        CardDetailScreen(navController)
                    }
                }
            }
        }
    }
}