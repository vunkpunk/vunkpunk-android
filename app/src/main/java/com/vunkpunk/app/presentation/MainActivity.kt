package com.vunkpunk.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vunkpunk.app.common.Constants.PARAM_CARD_ID
import com.vunkpunk.app.common.Constants.PARAM_EMAIL
import com.vunkpunk.app.common.Constants.PARAM_SEARCH
import com.vunkpunk.app.common.Token.TOKEN
import com.vunkpunk.app.presentation.card_detail.CardDetailScreen
import com.vunkpunk.app.presentation.main.MainScreen
import com.vunkpunk.app.presentation.profile.ProfileScreen
import com.vunkpunk.app.presentation.about.AboutScreen
import com.vunkpunk.app.presentation.create_card.PostCardScreen
import com.vunkpunk.app.presentation.login_system.code.CodeScreen
import com.vunkpunk.app.presentation.login_system.login.LoginScreen
import com.vunkpunk.app.presentation.login_system.sign_up.SignUpScreen
import com.vunkpunk.app.presentation.login_system.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var start = Screen.SearchScreen.route + "/{$PARAM_SEARCH}"
        if (TOKEN.value == ""){
            start = Screen.WelcomeScreen.route
        }
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        setContent {
            Surface() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = start
                ) {
                    composable(
                        route = Screen.SearchScreen.route + "/{$PARAM_SEARCH}"
                    ) {
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.MainScreen.route
                    ) {
                        MainScreen(navController)
                    }
                    composable(
                        route = Screen.PostCardScreen.route,
                    ) {
                        PostCardScreen(navController)
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
                    composable(
                        route = Screen.AboutScreen.route,
                    ) {
                        AboutScreen(navController)
                    }
                    composable(
                        route = Screen.WelcomeScreen.route,
                    ) {
                        WelcomeScreen(navController)
                    }
                    composable(
                        route = Screen.SignUpScreen.route,
                    ) {
                        SignUpScreen(navController)
                    }
                    composable(
                        route = Screen.CodeScreen.route + "/{$PARAM_EMAIL}",
                    ) {
                        CodeScreen(navController)
                    }
                    composable(
                        route = Screen.LoginScreen.route,
                    ) {
                        LoginScreen(navController)
                    }
                }
            }
        }
    }
}