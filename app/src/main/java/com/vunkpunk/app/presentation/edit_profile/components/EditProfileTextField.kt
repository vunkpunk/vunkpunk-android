package com.vunkpunk.app.presentation.edit_profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor


@Composable
fun EditProfileTextField(value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .background(color = Color.LightGray)
            .zIndex(1f)
            .fillMaxWidth()
            .height(50.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        BasicTextField(modifier = Modifier.fillMaxWidth(),
            value = value,
            textStyle = TextStyle(color = GeneralTextColor),
            onValueChange = { onValueChange(it) }) {
            Text(
                modifier = Modifier,
                text = value,
                fontSize = 16.sp,
                color = GeneralTextColor,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}