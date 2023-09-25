package com.berkkanb.mybaseapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "satellite_details")
data class SatelliteDetailEntity(
    @PrimaryKey
    val id: Int,
    @SerializedName("cost_per_launch") val costPerLaunch: Int,
    @SerializedName("first_flight") val firstFlight: String,
    val height: Int,
    val mass: Int
)
