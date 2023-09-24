package com.berkkanb.mybaseapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.mybaseapp.data.model.SatelliteUI
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val satelliteRepository: SatelliteRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    val uiState = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            val response = satelliteRepository.getSatelliteList()
            _uiState.update {
                it.copy(
                    satelliteList = response
                )
            }
        }
    }
}

data class HomeScreenUIState(
    val satelliteList: List<SatelliteUI> = emptyList()
)