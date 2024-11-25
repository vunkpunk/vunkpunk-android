package com.vunkpunk.app.presentation

sealed class Screen(val route: String) {
    object TestScreen : Screen("test_screen")
    object MainScreen : Screen("main_screen")
    object ProfileScreen : Screen("profile_screen")
}