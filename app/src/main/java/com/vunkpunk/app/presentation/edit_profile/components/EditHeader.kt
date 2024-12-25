package com.vunkpunk.app.presentation.edit_profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.vunkpunk.app.presentation.edit_profile.EditProfileViewModel
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorTextColor

@Composable
fun EditHeader(navController: NavController, viewModel: EditProfileViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {
        val user = viewModel.user.value.user!!
        val editProfileState by viewModel.editProfileState

        Image(
            bitmap = user.photo.asImageBitmap(),
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            contentDescription = "Artist image"
        )

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center
        ) {
            EditProfileTextField(
                editProfileState.first_name,
                { viewModel.editFirstName(it) }
            )
            EditProfileTextField(
                editProfileState.second_name,
                { viewModel.editSecondName(it) }
            )
        }
    }
}