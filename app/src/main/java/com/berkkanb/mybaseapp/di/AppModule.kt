package com.berkkanb.mybaseapp.di

import android.app.Application
import android.content.Context
import com.berkkanb.mybaseapp.data.source.AssetsDataSource
import com.berkkanb.mybaseapp.data.source.AssetsDataSourceImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAssetsDataSource(context: Context): AssetsDataSource {
        return AssetsDataSourceImpl(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}