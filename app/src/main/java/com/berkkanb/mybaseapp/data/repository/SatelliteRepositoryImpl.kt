package com.berkkanb.mybaseapp.data.repository

import com.berkkanb.mybaseapp.data.model.PositionUI
import com.berkkanb.mybaseapp.data.model.SatelliteDetailEntity
import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import com.berkkanb.mybaseapp.data.model.SatellitePositionUI
import com.berkkanb.mybaseapp.data.model.SatelliteUI
import com.berkkanb.mybaseapp.data.source.AssetsDataSource
import com.berkkanb.mybaseapp.data.source.database.SatelliteDetailDao
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource,
    private val gson: Gson,
    private val satelliteDetailDao: SatelliteDetailDao,
    private val satelliteDetailEntityMapper: SatelliteDetailEntityMapper
) : SatelliteRepository {
    override suspend fun getSatelliteList(): List<SatelliteUI> {
        val json = assetsDataSource.fetchJsonData("SATELLITE-LIST.JSON")
        val type = object : TypeToken<List<SatelliteUI>>() {}.type

        return gson.fromJson(json, type)
    }

    override suspend fun getSatelliteDetail(id: Int): SatelliteDetailUI? {
        val localeSatelliteDetail = satelliteDetailDao.getSatelliteDetail(id)
        if (localeSatelliteDetail != null) {
            return satelliteDetailEntityMapper.convert(localeSatelliteDetail)
        } else {
            val json = assetsDataSource.fetchJsonData("SATELLITE-DETAIL.JSON")
            val type = object : TypeToken<List<SatelliteDetailUI>>() {}.type

            val assetSatelliteList: List<SatelliteDetailUI> = gson.fromJson(json, type)
            satelliteDetailDao.insertSatelliteDetailList(
                satelliteDetailEntityMapper.convert(
                    assetSatelliteList
                )
            )

            return satelliteDetailDao.getSatelliteDetail(id)
                ?.let { satelliteDetailEntityMapper.convert(it) }
        }
    }

    override suspend fun getSatellitePositionList(id: Int): Flow<PositionUI> = flow {
        val json = assetsDataSource.fetchJsonData("POSITIONS.JSON")
        val type = object : TypeToken<List<SatellitePositionUI>>() {}.type

        val satellitePositionsGson: List<SatellitePositionUI> = gson.fromJson(json, type)
        val satellitePositionUI =
            satellitePositionsGson.first().list.find { it.id == id.toString() }

        satellitePositionUI?.let {
            for (positionUI in it.positions) {
                emit(positionUI)
                delay(3000)
            }
        }
    }
}

class SatelliteDetailEntityMapper @Inject constructor() {
    //TODO
    /*fun convert(data: List<SatelliteDetailEntity>): List<SatelliteDetailUI> {
        return data.map {
            SatelliteDetailUI(
                it.id,
                it.costPerLaunch,
                it.firstFlight,
                it.height,
                it.mass
            )
        }
    }*/

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

    fun convert(data: SatelliteDetailUI): SatelliteDetailEntity {
        return SatelliteDetailEntity(
            data.id,
            data.costPerLaunch,
            data.firstFlight,
            data.height,
            data.mass
        )
    }
}