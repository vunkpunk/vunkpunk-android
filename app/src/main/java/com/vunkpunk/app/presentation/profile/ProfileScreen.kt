package com.vunkpunk.app.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vunkpunk.app.R
import com.vunkpunk.app.domain.model.CardMini
import com.vunkpunk.app.presentation.main.components.Card
import com.vunkpunk.app.presentation.profile.components.Header
import com.vunkpunk.app.presentation.profile.components.HistoryDivider
import com.vunkpunk.app.presentation.profile.components.ProfileDescription


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user = viewModel.user.value
    val cards: List<CardMini> = viewModel.cards.value.cardsMini

    Column(modifier = Modifier
        .wrapContentSize()
        .verticalScroll(rememberScrollState())
        .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(user.user)

        Spacer(modifier = Modifier.height(50.dp))

        ProfileDescription()

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier.height(1000.dp)) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalItemSpacing = 20.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(cards) { card -> Card(card = card) }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        HistoryDivider()

        Spacer(modifier = Modifier.height(20.dp))

        Box(modifier = Modifier.height(1000.dp)) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalItemSpacing = 20.dp,
                modifier = Modifier.fillMaxSize()
            ) {
                items(cards) { card -> Card(card = card) }
            }
        }

        }
    }