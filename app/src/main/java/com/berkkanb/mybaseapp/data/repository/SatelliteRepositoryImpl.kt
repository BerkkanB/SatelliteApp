package com.berkkanb.mybaseapp.data.repository

import com.berkkanb.mybaseapp.data.mapper.SatelliteDetailEntityMapper
import com.berkkanb.mybaseapp.data.model.PositionUI
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
