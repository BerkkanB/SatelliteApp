package com.berkkanb.mybaseapp.data.model

data class SatelliteUI(
    val id: Int,
    val active: Boolean,
    val name: String
){
    fun doesMatchSearchQuery(query: String): Boolean {
        return this.name.contains(query, ignoreCase = true)
    }
}
