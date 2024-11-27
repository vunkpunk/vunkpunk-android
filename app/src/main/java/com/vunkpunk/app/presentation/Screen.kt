package com.vunkpunk.app.presentation

import com.vunkpunk.app.common.Constants.PARAM_CARD_ID

sealed class Screen(val route: String) {
    object TestScreen : Screen("test_screen")
    object MainScreen : Screen("main_screen")
    object ProfileScreen : Screen("profile_screen")
    object CardDetailScreen: Screen("card_detail_screen/{$PARAM_CARD_ID}")
    object AboutScreen: Screen("about_screen")
}