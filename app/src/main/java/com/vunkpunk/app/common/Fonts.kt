package com.vunkpunk.app.common

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.vunkpunk.app.R


object Fonts {

    val robotoMonoFamily = FontFamily(
        Font(R.font.roboto_mono_light, FontWeight.Light),
        Font(R.font.roboto_mono_regular, FontWeight.Normal),
        Font(R.font.roboto_mono_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.roboto_mono_medium, FontWeight.Medium),
        Font(R.font.roboto_mono_bold, FontWeight.Bold)
    )
}