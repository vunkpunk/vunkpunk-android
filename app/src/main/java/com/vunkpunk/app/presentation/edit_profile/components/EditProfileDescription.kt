package com.vunkpunk.app.presentation.edit_profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.vunkpunk.app.R
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.edit_profile.EditProfileViewModel
import com.vunkpunk.app.presentation.theme.GeneralBackgroundColor
import com.vunkpunk.app.presentation.theme.GeneralTextColor
import com.vunkpunk.app.presentation.theme.MinorTextColor


@Composable
fun EditProfileDescription(navController: NavController, viewModel: EditProfileViewModel) {
    val user = viewModel.user.value.user!!
    val editProfileState by viewModel.editProfileState

    Surface(
        modifier = Modifier
            .background(color = GeneralBackgroundColor),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = rememberAsyncImagePainter(R.drawable.home),
                    contentDescription = null
                )
                EditProfileTextField(
                    editProfileState.dormitory,
                    { viewModel.editDormitory(it) }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = painterResource(id = R.drawable.faculty),
                    contentDescription = null
                )
                EditProfileTextField(
                    editProfileState.faculty,
                    { viewModel.editFaculty(it) }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(0.dp, 10.dp, 0.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 10.dp, 0.dp),
                    painter = painterResource(id = R.drawable.contact),
                    contentDescription = null
                )
                EditProfileTextField(
                    editProfileState.contact,
                    { viewModel.editContact(it) }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "О себе:", fontSize = 20.sp, color = GeneralTextColor)
                }
            }
            EditProfileTextField(
                editProfileState.description,
                { viewModel.editDescription(it) }
            )
        }
    }
}
