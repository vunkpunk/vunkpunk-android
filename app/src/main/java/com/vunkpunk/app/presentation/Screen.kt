package com.vunkpunk.app.presentation

sealed class Screen(val route: String) {
    object TestScreen: Screen("test_screen")
}