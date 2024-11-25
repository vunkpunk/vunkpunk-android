package com.vunkpunk.app.presentation.card_detail.components

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vunkpunk.app.R
import com.vunkpunk.app.domain.model.CardMini

@Preview(showBackground = true)
@Composable
fun Comment() {
    Column(modifier = Modifier.padding(10.dp)) {
        // header with Name Surname
        Row(modifier = Modifier
            .height(30.dp)
            .fillMaxWidth()){
            // Avatar
            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null, modifier = Modifier
                .size(30.dp)
                .clip(CircleShape))
            Spacer(modifier = Modifier.fillMaxHeight().width(10.dp))

            // Name Surname
            Column {
                Text(text = "Имя Фамилия")
                RatingStars(3.toFloat())
            }
        }
        // Comment
        Spacer(modifier = Modifier.height(10.dp).fillMaxWidth())
        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et")

    }



}
