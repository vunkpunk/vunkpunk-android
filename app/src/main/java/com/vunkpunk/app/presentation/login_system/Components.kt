package com.vunkpunk.app.presentation.login_system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.vunkpunk.app.R

@Composable
fun Background(){
    Image(
        painter = painterResource(R.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        alignment = Alignment.TopCenter
    )
}


fun pageIndicator(page: Int): Int {
    return when(page){
        1 -> R.drawable.page_indicator_1
        2 -> R.drawable.page_indicator_2
        3 -> R.drawable.page_indicator_3
        else -> {R.drawable.page_indicator_0}
    }
}


