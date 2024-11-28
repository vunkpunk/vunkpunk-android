package com.vunkpunk.app.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun HistoryDivider() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Center
    ) {
        HorizontalDivider(modifier = Modifier
            .width(100.dp)
            .padding(0.dp, 0.dp, 15.dp, 0.dp),
            thickness = 1.dp)
        Text( text = "История", fontSize = 24.sp)
        HorizontalDivider(modifier = Modifier
            .width(100.dp)
            .padding(15.dp, 0.dp, 0.dp, 0.dp),
            thickness = 1.dp)
    }
}