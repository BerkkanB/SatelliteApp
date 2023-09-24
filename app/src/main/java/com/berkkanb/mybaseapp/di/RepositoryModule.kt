package com.berkkanb.mybaseapp.di

import com.berkkanb.mybaseapp.data.repository.SatelliteRepositoryImpl
import com.berkkanb.mybaseapp.domain.SatelliteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideSatelliteRepository(repositoryImpl: SatelliteRepositoryImpl): SatelliteRepository
}