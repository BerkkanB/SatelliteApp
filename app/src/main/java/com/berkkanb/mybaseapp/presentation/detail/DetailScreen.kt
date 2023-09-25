package com.berkkanb.mybaseapp.presentation.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel(),
) {
    val uiState by detailScreenViewModel.uiState.collectAsState()
    
    Text(text = uiState.satelliteDetail?.firstFlight ?: "")
}