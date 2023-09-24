package com.berkkanb.mybaseapp.data.source

interface AssetsDataSource {
    suspend fun fetchJsonData(fileName: String): String
}