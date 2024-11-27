package com.vunkpunk.app.presentation.global_components.HeadNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vunkpunk.app.R
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorTextColor

@Preview
@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(GeneralBackgroundColor, RoundedCornerShape(20.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Spacer(modifier = Modifier.width(20.dp))

        // BasicTextField
        BasicTextField(modifier = Modifier
            .height(20.dp)
            .weight(1f),
            value = text,
            onValueChange = {text = it}){

            // Placeholder (исчезает при вводе текста)
            if (text == "") {
                Text(
                    text = "Поиск",
                    style = TextStyle(fontSize = 16.sp, color = MinorTextColor)
                )
            } else {
                Text(modifier = Modifier
                    .horizontalScroll(scrollState),
                    text = text,
                    fontSize = 16.sp,
                    color = GeneralTextColor,
                    overflow = TextOverflow.Ellipsis)

                LaunchedEffect(text) {
                    scrollState.scrollTo(scrollState.maxValue)
                }
            }
        }

        Spacer(modifier = Modifier.width(40.dp))

        Image(modifier = Modifier
            .size(20.dp),
            painter = painterResource(id = R.drawable.home),
            contentDescription = "")

        Spacer(modifier = Modifier.width(20.dp))

    }
}