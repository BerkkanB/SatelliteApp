package com.berkkanb.mybaseapp.data.repository

import com.berkkanb.mybaseapp.data.model.SatelliteDetailUI
import com.berkkanb.mybaseapp.data.model.SatelliteUI
import com.berkkanb.mybaseapp.data.source.AssetsDataSource
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val assetsDataSource: AssetsDataSource,
    private val gson: Gson
) : SatelliteRepository {
    override suspend fun getSatelliteList(): List<SatelliteUI> {
        val json = assetsDataSource.fetchJsonData("SATELLITE-LIST.JSON")
        val type = object : TypeToken<List<SatelliteUI>>() {}.type
        return gson.fromJson(json, type)
    }

    override suspend fun getSatelliteDetail(id: Int): List<SatelliteDetailUI> {
        val json = assetsDataSource.fetchJsonData("SATELLITE-DETAIL.JSON")
        val type = object : TypeToken<List<SatelliteDetailUI>>() {}.type
        return gson.fromJson(json, type)
    }
}