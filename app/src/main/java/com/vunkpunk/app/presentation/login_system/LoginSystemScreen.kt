package com.vunkpunk.app.presentation.login_system

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.vunkpunk.app.presentation.login_system.screens.SignUpScreen
import com.vunkpunk.app.presentation.login_system.screens.Welcome


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginSystemScreen() {
    MaterialTheme {
        val pagerState = rememberPagerState(pageCount = { 3 })

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> Welcome()
                1 -> SignUpScreen()
                2 -> ScreenOne()
            }
        }
    }
}

@Composable
fun ScreenOne() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 1", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun ScreenTwo() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Screen 2", style = MaterialTheme.typography.headlineMedium)
    }
}

