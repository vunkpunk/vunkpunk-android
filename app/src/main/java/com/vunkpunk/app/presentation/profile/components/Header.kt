package com.vunkpunk.app.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vunkpunk.app.R
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.domain.model.User
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor

@Composable
fun Header(user: User) {
    Surface(
        modifier = Modifier
            .height(165.dp),
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = GeneralBackgroundColor),
            verticalAlignment = Alignment.CenterVertically,
        )
        {

            Spacer(modifier = Modifier.width(20.dp))
            Image(
                bitmap = user.photo.asImageBitmap(),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(125.dp),
                contentDescription = "Artist image",
                contentScale = ContentScale.FillBounds
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = user.first_name, fontSize = 25.sp, fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Normal, color = GeneralTextColor
                )
                Text(
                    text = user.last_name, fontSize = 25.sp, fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Normal, color = GeneralTextColor
                )
            }

            Spacer(modifier = Modifier.width(20.dp))
        }
    }
}