package com.berkkanb.mybaseapp.data.mapper

import com.berkkanb.mybaseapp.data.model.SatelliteDetailEntity
import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import javax.inject.Inject


class SatelliteDetailEntityMapper @Inject constructor() {

    fun convert(data: List<SatelliteDetailUI>): List<SatelliteDetailEntity> {
        return data.map {
            SatelliteDetailEntity(
                it.id,
                it.costPerLaunch,
                it.firstFlight,
                it.height,
                it.mass
            )
        }
    }

    fun convert(data: SatelliteDetailEntity): SatelliteDetailUI {
        return SatelliteDetailUI(
            data.id,
            data.costPerLaunch,
            data.firstFlight,
            data.height,
            data.mass
        )
    }

}