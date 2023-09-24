package com.berkkanb.mybaseapp.data.source

import android.content.Context

class AssetsDataSourceImpl(private val context: Context) : AssetsDataSource {
    override suspend fun fetchJsonData(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}