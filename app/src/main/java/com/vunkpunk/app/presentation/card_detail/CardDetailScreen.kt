package com.vunkpunk.app.presentation.card_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.domain.model.CardDetail
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.card_detail.components.Comment
import com.vunkpunk.app.presentation.global_components.BottomNavigation
import com.vunkpunk.app.presentation.main.MainViewModel
import com.vunkpunk.app.presentation.profile.ProfileViewModel

@Composable
fun CardDetailScreen(
    navController: NavController,
    viewModel: CardDetailViewModel = hiltViewModel()
) {

    Scaffold(
        bottomBar = {
            BottomNavigation(navController)
        }
    ) { innerPadding ->
        Content(navController = navController, viewModel = viewModel, padding = innerPadding)
    }
}

@Composable
fun Content(navController: NavController, viewModel: CardDetailViewModel, padding: PaddingValues) {
    val state = viewModel.state.value
    val card = state.cardDetail

    if (card == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(state.error, modifier = Modifier.align(Alignment.Center))
        }
    } else {
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                item {
                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                    )

                    Text(
                        text = card.title,
                        fontSize = 32.sp,
                        modifier = Modifier.size(360.dp, 40.dp)
                    )

                    Spacer(
                        modifier = Modifier
                            .height(30.dp)
                            .fillMaxWidth()
                    )
                }

                // Images
                item {
                    Column(modifier = Modifier.size(360.dp)) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .size(360.dp, 250.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxWidth()
                        )
                        LazyRow(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .size(360.dp, 50.dp),
                            horizontalArrangement = Arrangement.spacedBy(30.dp),
                        ) {
                            items(20) {
                                Image(
                                    painter = painterResource(R.drawable.ic_launcher_background),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(50.dp)
                                )
                            }
                        }
                    }
                }

                // Price
                item {
                    Text(
                        text = "Цена: ${card.price}",
                        fontSize = 26.sp,
                        modifier = Modifier.size(360.dp, 40.dp)
                    )
                }

                // Description
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier
                            .width(360.dp)
                            .wrapContentHeight()
                            .background(color = Color.Red)
                            .padding(10.dp)
                    ) {
                        Text(card.description)
                        Text("Контакт: НУЖУЕН КОНТАКТ")
                    }
                }
                // Comments
                item {
                    Spacer(
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth()
                    )
                    HorizontalDivider(thickness = 2.dp, color = Color.Black)
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                    )
                }
                items(10) {
                    Comment()
                }

            }
        }
    }
}
