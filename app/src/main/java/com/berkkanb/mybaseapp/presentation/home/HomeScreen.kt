package com.berkkanb.mybaseapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.berkkanb.mybaseapp.data.model.SatelliteUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
    navigateToDetailScreen: (id: String) -> Unit
) {

    val uiState by homeScreenViewModel.uiState.collectAsState()

    LazyColumn() {
        item {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
                    .padding(vertical = 20.dp),
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Search")
                }
            )
        }
        items(uiState.satelliteList) { satellite ->
            SatelliteItem(item = satellite, onClickItem = navigateToDetailScreen)
        }
    }

}

@Composable
fun SatelliteItem(
    item: SatelliteUI,
    onClickItem: (String) -> Unit
) {
    val statusColor = if (item.active) Color.Green else Color.Red
    val statusTitle = if (item.active) "Active" else "Passive"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
            .padding(vertical = 5.dp)
            .clickable { onClickItem.invoke(item.id.toString()) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(statusColor, RoundedCornerShape(100))
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Text(
                text = statusTitle,
                modifier = Modifier.padding(top = 6.dp),
                color = Color.LightGray
            )
        }
    }
}