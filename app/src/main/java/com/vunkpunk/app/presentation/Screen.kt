package com.vunkpunk.app.presentation

import com.vunkpunk.app.common.Constants.PARAM_CARD_ID
import com.vunkpunk.app.common.Constants.PARAM_EMAIL
import com.vunkpunk.app.common.Constants.PARAM_SEARCH

sealed class Screen(val route: String) {
    object TestScreen : Screen("test_screen")
    object SearchScreen : Screen("search_screen")
    object MainScreen : Screen("main_screen/{$PARAM_SEARCH}")
    object PostCardScreen : Screen("post_card_screen")
    object ProfileScreen : Screen("profile_screen")
    object EditProfileScreen : Screen("edit_profile_screen")
    object CardDetailScreen: Screen("card_detail_screen/{$PARAM_CARD_ID}")
    object AboutScreen: Screen("about_screen")
    object WelcomeScreen: Screen("welcome")
    object SignUpScreen: Screen("sign_up")
    object LoginScreen: Screen("login")
    object CodeScreen: Screen("code/{$PARAM_EMAIL}")
}