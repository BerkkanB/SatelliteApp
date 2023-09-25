package com.berkkanb.mybaseapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berkkanb.mybaseapp.data.model.SatelliteUI
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val satelliteRepository: SatelliteRepository
) : ViewModel() {


    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val emptySatelliteList = emptyList<SatelliteUI>()
    private val _satelliteList = MutableStateFlow(emptySatelliteList)

    init {
        viewModelScope.launch {
            val response = satelliteRepository.getSatelliteList()
            _satelliteList.value = response
        }
    }

    val satelliteList = searchText
        .debounce(400L)
        .onEach { _isSearching.update { true } }
        .combine(_satelliteList) { text, satellites ->
            if (text.isBlank()) {
                satellites
            } else {
                delay(1000L)
                satellites.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _satelliteList.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class HomeScreenUIState(
    val satelliteList: List<SatelliteUI> = emptyList()
)