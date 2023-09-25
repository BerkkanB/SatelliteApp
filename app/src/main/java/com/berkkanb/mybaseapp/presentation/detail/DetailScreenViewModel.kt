package com.berkkanb.mybaseapp.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.mybaseapp.data.model.PositionUI
import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val satelliteRepository: SatelliteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val detailId: String = checkNotNull(savedStateHandle["detailId"])
    private val title: String = checkNotNull(savedStateHandle["title"])

    private val _uiState = MutableStateFlow(DetailScreenUIState(title = title))
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            collectPositionData()
            val response = satelliteRepository.getSatelliteDetail(detailId.toInt())
            _uiState.update {
                it.copy(satelliteDetail = response)
            }
        }
    }

    private fun collectPositionData() {
        viewModelScope.launch {
            satelliteRepository.getSatellitePositionList(detailId.toInt()).collect { position ->
                _uiState.update {
                    it.copy(position = position)
                }
            }
        }
    }
}

data class DetailScreenUIState(
    val satelliteDetail: SatelliteDetailUI? = null,
    val position: PositionUI? = null,
    val title: String
)