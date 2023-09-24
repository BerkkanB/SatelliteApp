package com.berkkanb.mybaseapp.domain

import com.berkkanb.mybaseapp.data.model.SatelliteUI

interface SatelliteRepository {
    suspend fun getSatelliteList():List<SatelliteUI>
}