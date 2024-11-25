package com.vunkpunk.app.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.vunkpunk.app.R
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor


@Preview(showBackground = true)
@Composable
fun ProfileDescription() {

    Surface(modifier = Modifier
        .background(color = GeneralBackgroundColor),
        shadowElevation = 4.dp
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp)
            .background(color = GeneralBackgroundColor)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = rememberAsyncImagePainter(R.drawable.home),
                    contentDescription = null)
                Text(text = "21 общежитие", fontSize = 20.sp, color = GeneralTextColor)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = painterResource(id = R.drawable.faculty),
                    contentDescription = null)
                Text(text = "Матмех", fontSize = 20.sp, color = GeneralTextColor)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Image(modifier = Modifier
                    .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = painterResource(id = R.drawable.contact),
                    contentDescription = null)
                Text(text = "@iraedeus", fontSize = 20.sp, color = GeneralTextColor)
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "О себе:", fontSize = 20.sp, color = GeneralTextColor)
                }
            }
            Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." +
                    " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat." +
                    " Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur." +
                    " Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id es", fontSize = 16.sp, color = GeneralTextColor)

        }
    }
    }