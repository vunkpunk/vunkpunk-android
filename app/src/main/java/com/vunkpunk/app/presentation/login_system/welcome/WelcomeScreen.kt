package com.vunkpunk.app.presentation.login_system.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.common.Fonts.robotoMonoFamily
import com.vunkpunk.app.presentation.Screen
import com.vunkpunk.app.presentation.login_system.Background
import com.vunkpunk.app.presentation.login_system.pageIndicator


@Composable
fun WelcomeScreen(
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Background()
        Box(modifier = Modifier
            .fillMaxSize()
            .clickable { navController.navigate(Screen.SignUpScreen.route) }) {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
                Text(
                    text = stringResource(R.string.welcome_to),
                    fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 34.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(R.string.capital_app),
                    fontFamily = robotoMonoFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 34.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                )
                Box(modifier = Modifier.size(500.dp)) {
                    Image(
                        painter = painterResource(R.drawable.laptop_boy),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 20.dp)
                    )
                }
            }

            //Dots
            Image(
                painter = painterResource(pageIndicator(1)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .offset(y = (-30).dp),
            )
        }
    }
}