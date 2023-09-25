package com.berkkanb.mybaseapp.data.source.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.berkkanb.mybaseapp.data.model.SatelliteDetailEntity

@Dao
interface SatelliteDetailDao {
    @Query("SELECT * FROM satellite_details WHERE id = :id")
    suspend fun getSatelliteDetail(id: Int): SatelliteDetailEntity?

    @Insert
    suspend fun insertSatelliteDetail(detail: SatelliteDetailEntity)

    @Insert
    suspend fun insertSatelliteDetailList(details: List<SatelliteDetailEntity>)

}
