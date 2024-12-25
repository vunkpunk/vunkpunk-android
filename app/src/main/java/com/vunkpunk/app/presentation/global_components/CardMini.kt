package com.vunkpunk.app.presentation.global_components

import android.graphics.RenderEffect
import android.graphics.Shader
import com.vunkpunk.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.theme.CardBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor

@Composable
fun CardMini(card: CardMini, navController: NavController) {
    val cardPrice = card.price
    var price = cardPrice
    if (cardPrice.isDigitsOnly()) {
        price += " ₽"
    }
    val title = card.title
    val dormitory = card.dormitory
    val cardId = card.id
    Surface(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .size(width = 190.dp, height = 200.dp)
            .clickable { navController.navigate(Screen.CardDetailScreen.route + "/$cardId") },
        color = CardBackgroundColor.copy(alpha = 0.5f), // Полупрозрачный фон
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Image(
                bitmap = card.photo.asImageBitmap(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .size(155.dp, 100.dp)
                    .clip(RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
            )
            Column {
                Text(
                    title,
                    overflow = TextOverflow.Ellipsis,
//                        color = GeneralTextColor,
                    modifier = Modifier
                        .size(130.dp, 32.dp)
                        .align(Alignment.CenterHorizontally),
                    fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
                Spacer(modifier = Modifier.size(130.dp, 10.dp))

                Row(modifier = Modifier.width(155.dp)) {
                    Spacer(modifier = Modifier.width(15.dp))
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(16.dp)
                    ) {
                        Text(
                            "$dormitory общ",
                            modifier = Modifier.size(60.dp, 30.dp),
                            color = Color.Gray,
                            fontFamily = robotoMonoFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(105.dp)
                            .height(16.dp)
                    ) {
                        Text(
                            price,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.BottomEnd),
                            color = Color.Gray,
                            fontFamily = robotoMonoFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp, 30.dp))
                }
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

