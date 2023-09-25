package com.berkkanb.mybaseapp.domain

import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import com.berkkanb.mybaseapp.data.model.SatellitePositionUI
import com.berkkanb.mybaseapp.data.model.SatelliteUI

interface SatelliteRepository {
    suspend fun getSatelliteList(): List<SatelliteUI>

    suspend fun getSatelliteDetail(id: Int): SatelliteDetailUI?

    suspend fun getSatellitePositionList(id: Int): List<SatellitePositionUI>
}