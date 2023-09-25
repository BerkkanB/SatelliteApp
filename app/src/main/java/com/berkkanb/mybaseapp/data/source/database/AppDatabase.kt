package com.berkkanb.mybaseapp.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berkkanb.mybaseapp.data.model.SatelliteDetailEntity

@Database(entities = [SatelliteDetailEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun satelliteDetailDao(): SatelliteDetailDao
}