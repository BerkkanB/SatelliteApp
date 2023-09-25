package com.berkkanb.mybaseapp.data.model

import com.google.gson.annotations.SerializedName

data class PositionUI(
    @SerializedName("posX") val posX: Double,
    @SerializedName("posY") val posY: Double
)

data class PositionItem(
    @SerializedName("id") val id: String,
    @SerializedName("positions") val positions: List<PositionUI>
)

data class SatellitePositionUI(
    @SerializedName("list") val list: List<PositionItem>
)