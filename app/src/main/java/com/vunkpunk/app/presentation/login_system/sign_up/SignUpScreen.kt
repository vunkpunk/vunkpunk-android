package com.vunkpunk.app.presentation.login_system.sign_up

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.login_system.Background
import com.vunkpunk.app.presentation.login_system.LoginSystemEvent
import com.vunkpunk.app.presentation.login_system.pageIndicator


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val state by viewModel.state

    val dummy = (100000..999999).random()

    var email by remember { mutableStateOf("st$dummy@student.spbu.ru") }
    var password by remember { mutableStateOf("exampe123") }
    var passwordVerification by remember { mutableStateOf("exampe123") }
    val context = LocalContext.current
    var error by remember { state.error }
    var isAuth by remember { state.isAuth }
    LaunchedEffect(error) {
        if (error.isNotEmpty()) {
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            error = ""
        }
    }
    LaunchedEffect(isAuth) {
        if (isAuth) {
            navController.navigate(Screen.CodeScreen.route + "/${email}")
        }
    }
    state.password = password
    state.email = email
    state.userName = email


    Box(modifier = Modifier.fillMaxSize()) {
        Background()

        Column {
            // Create account Text
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )
            Text(
                text = stringResource(R.string.create_account),
                fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.with_st_mail),
                fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )

            // Fields
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .size(380.dp, 550.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(35.dp))
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 25.dp, vertical = 35.dp)
            ) {
                Text(
                    text = stringResource(R.string.sign_up),
                    fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    modifier = Modifier,
                    textAlign = TextAlign.Center
                )

                // St mail
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = robotoMonoFamily,
                        fontSize = 17.sp,
                    ),
                    label = { Text(stringResource(R.string.st_mail)) },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                email = ""
                            }
                        })

                // Password
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = robotoMonoFamily,
                        fontSize = 17.sp,
                    ),
                    label = { Text(stringResource(R.string.password)) },
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                password = ""
                            }
                        }
                )

                //PasswordVerification
                TextField(
                    value = passwordVerification,
                    onValueChange = { passwordVerification = it },
                    label = { Text(stringResource(R.string.password_verification)) },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = robotoMonoFamily,
                        fontSize = 17.sp,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    singleLine = true,
                    shape = CircleShape,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                passwordVerification = ""
                            }
                        })

                // Button
                Button(
                    onClick = {
                        error = validateData(email, password, passwordVerification, context)
                        if (error == "") {
                            viewModel.onEvent(LoginSystemEvent.SignUp)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.sign_up))
                }

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.5f),
                    thickness = 1.dp,
                    modifier = Modifier
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.already_have_an_account),
                        fontFamily = robotoMonoFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = stringResource(R.string.log_in),
                        fontFamily = robotoMonoFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.clickable { navController.navigate(Screen.LoginScreen.route) }
                    )
                }
            }
        }

        // Dots
        Image(
            painter = painterResource(pageIndicator(2)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = (-30).dp),
        )
    }
}

fun validateData(
    mail: String,
    password: String,
    passwordVerification: String,
    context: Context
): String {
    val pattern = Regex("^st\\d{6}@student\\.spbu\\.ru$")
    if (!pattern.matches(mail)) {
        return context.getString(R.string.email_error)
    }
    if (password != passwordVerification) {
        return context.getString(R.string.not_same_password_error)
    }
    if (password.length < 5) {
        return context.getString(R.string.password_length_error)
    }
    if (password.contains(" ")) {
        return context.getString(R.string.password_space_error)
    }
    if (!password.any { it.isDigit() }) {
        return context.getString(R.string.password_digit_error)
    }
    if (!password.any { it.isLetter() }) {
        return context.getString(R.string.password_char_error)
    }
    return ""
}
