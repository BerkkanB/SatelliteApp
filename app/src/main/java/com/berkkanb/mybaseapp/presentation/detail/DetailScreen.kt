package com.berkkanb.mybaseapp.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    detailScreenViewModel: DetailScreenViewModel = hiltViewModel(),
) {
    val uiState by detailScreenViewModel.uiState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        uiState.satelliteDetail?.let {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "TITLE", fontWeight = FontWeight.ExtraBold)
                Text(text = it.firstFlight, fontWeight = FontWeight.ExtraLight, modifier = Modifier.padding(top = 16.dp))
                Row(modifier = Modifier.padding(top = 36.dp)) {
                    Text(text = "Height/Mass:", fontWeight = FontWeight.Bold)
                    Text(text = "${it.height/it.mass}")
                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    Text(text = "Cost:", fontWeight = FontWeight.Bold)
                    Text(text = it.costPerLaunch.toString())
                }
                Row(modifier = Modifier.padding(top = 16.dp)) {
                    Text(text = "Last Position:", fontWeight = FontWeight.Bold)
                    Text(text = "last pos")
                }
            }
        }
    }
}