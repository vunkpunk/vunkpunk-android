package com.vunkpunk.app.presentation.card_detail.components

import com.vunkpunk.app.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingStars(rating: Float) {
    val maxRating = 5
    Box(modifier = Modifier.wrapContentSize()) {

        Row(modifier = Modifier)
        {
            for (i in 1..maxRating) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                ) {
                    if (rating > i - 1) {
                        val fraction = (rating - (i - 1))
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "Empty Star",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(androidx.compose.ui.graphics.RectangleShape)
                                .drawWithContent {
                                    val widthFraction = size.width * fraction.coerceIn(0f, 1f)
                                    clipRect(0f, 0f, widthFraction, size.height) {
                                        this@drawWithContent.drawContent()
                                    }
                                }
                        )

                    }
                }
            }
        }
    }
}
