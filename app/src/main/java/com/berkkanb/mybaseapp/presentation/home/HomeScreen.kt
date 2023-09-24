package com.berkkanb.mybaseapp.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val uiState by homeScreenViewModel.uiState.collectAsState()

    if (uiState.satelliteList.isEmpty().not()){
        Text(text = uiState.satelliteList.first().name)
    }

}