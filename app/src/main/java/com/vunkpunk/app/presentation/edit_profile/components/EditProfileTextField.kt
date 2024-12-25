package com.vunkpunk.app.presentation.edit_profile.components

import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorBackgroundColor


@Composable
private fun getBottomLineShape(lineThicknessDp: Dp) : Shape {
    val lineThicknessPx = with(LocalDensity.current) {lineThicknessDp.toPx()}
    return GenericShape { size, _ ->
        // 1) Bottom-left corner
        moveTo(0f, size.height)
        // 2) Bottom-right corner
        lineTo(size.width, size.height)
        // 3) Top-right corner
        lineTo(size.width, size.height - lineThicknessPx)
        // 4) Top-left corner
        lineTo(0f, size.height - lineThicknessPx)
    }
}


@Composable
fun EditProfileTextField(value: String, onValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .zIndex(1f)
            .heightIn(30.dp, 30.dp)
            .widthIn(100.dp, 150.dp)
            .background(GeneralBackgroundColor)
            .border(width = 1.dp, color = Color.Black, shape = getBottomLineShape(1.dp))
    ) {
        BasicTextField(modifier = Modifier.fillMaxSize(),
            value = value,
            textStyle = TextStyle(color = GeneralTextColor),
            onValueChange = { onValueChange(it) }) {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = value,
                fontSize = 20.sp, fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Normal,
                color = GeneralTextColor,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}