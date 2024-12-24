package com.vunkpunk.app.presentation.login_system.code

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun CodeScreen(
    navController: NavController,
    viewModel: CodeViewModel = hiltViewModel()
) {
    var code by remember { mutableStateOf("Code") }
    val state by viewModel.state
    val email = state.email
    val emailStPart = email.slice((0..7))
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
            navController.navigate(Screen.LoginScreen.route)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Background()

        Column {
            // Code Text
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            )
            Text(
                text = stringResource(R.string.secret_code),
                fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.was_sent_to),
                fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 34.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                text = emailStPart,
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
                    .size(380.dp, 380.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(35.dp))
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 25.dp, vertical = 35.dp)
            ) {
                Text(
                    text = stringResource(R.string.enter_code),
                    fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp,
                    modifier = Modifier,
                    textAlign = TextAlign.Center
                )

                // Code Field
                TextField(
                    value = code,
                    onValueChange = { code = it },
                    label = { Text(stringResource(R.string.code_field_label)) },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Center,
                        fontFamily = robotoMonoFamily,
                        fontSize = 17.sp,
                    ),
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
                                code = ""
                            }
                        })

                // Button
                Button(
                    onClick = {
                        state.code = code
                        viewModel.onEvent(LoginSystemEvent.SendCode)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.accept_code_button))
                }

                HorizontalDivider(
                    color = Color.Gray.copy(alpha = 0.5f), // Цвет линии
                    thickness = 1.dp,   // Толщина линии
                    modifier = Modifier // Отступы сверху и снизу
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    horizontalArrangement = Arrangement.Center, // Горизонтальное выравнивание по центру
                    verticalAlignment = Alignment.CenterVertically // Вертикальное выравнивание по центру
                ) {
                    Text(
                        text = stringResource(R.string.no_code_question),
                        fontFamily = robotoMonoFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                    )
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(
                        text = stringResource(R.string.resend_code),
                        fontFamily = robotoMonoFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    )
                }


            }

        }

        // Dots
        Image(
            painter = painterResource(pageIndicator(3)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .offset(y = (-30).dp),
        )
    }
}