package com.berkkanb.mybaseapp.domain

import com.berkkanb.mybaseapp.data.model.PositionUI
import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import com.berkkanb.mybaseapp.data.model.SatelliteUI
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    suspend fun getSatelliteList(): List<SatelliteUI>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailUI?

    suspend fun getSatellitePositionList(id: Int): Flow<PositionUI>
}