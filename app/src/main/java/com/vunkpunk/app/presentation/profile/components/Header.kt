package com.vunkpunk.app.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vunkpunk.app.R
import com.vunkpunk.app.domain.model.User
import com.vunkpunk.app.presentation.theme.GeneralTextColor

@Composable
fun Header(user: User?) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {

        Image(
            modifier = Modifier.clip(CircleShape),
            painter = painterResource(id = R.drawable.profile_avatar),
            contentDescription = "Artist image"
        )

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "First Name", fontSize = 20.sp, color = GeneralTextColor)
            Text(text = "Second Name", fontSize = 20.sp, color = GeneralTextColor)
        }
    }
}