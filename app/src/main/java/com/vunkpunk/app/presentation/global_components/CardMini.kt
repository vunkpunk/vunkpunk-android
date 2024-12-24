package com.vunkpunk.app.presentation.global_components

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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor

@Composable
fun CardMini(card: CardMini, navController: NavController) {
    Surface(
        modifier = Modifier
            .size(width = 150.dp, height = 290.dp)
            .wrapContentSize(Alignment.Center)
            .clickable { navController.navigate(Screen.CardDetailScreen.route + "/${card.id}") },
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {

            Image(
                bitmap = card.photo.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
            )
            Box(
                modifier = Modifier
                    .size(150.dp, 140.dp)
                    .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp))
                    .background(GeneralBackgroundColor)
                    .padding(10.dp)
            ) {
                Column {
                    Text(
                        card.title,
                        overflow = TextOverflow.Ellipsis,
                        color = GeneralTextColor,
                        modifier = Modifier.size(130.dp, 40.dp)
                    )
                    Spacer(modifier = Modifier.size(130.dp, 10.dp))
                    Text(
                        "${card.dormitory} Общежитие",
                        modifier = Modifier.size(130.dp, 30.dp),
                        color = GeneralTextColor
                    )
                    Row {
                        Box(Modifier.size(70.dp, 30.dp)) {
                            Text(card.price, modifier = Modifier.align(Alignment.CenterStart))
                        }
                        Spacer(modifier = Modifier.size(5.dp, 30.dp))
                        Box(Modifier.size(25.dp, 30.dp)) {
                            Text(
                                card.rating.toString(),
                                textAlign = TextAlign.Center,
                                color = GeneralTextColor,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp, 30.dp)
                        )
                    }
                }
            }
        }
    }

}
