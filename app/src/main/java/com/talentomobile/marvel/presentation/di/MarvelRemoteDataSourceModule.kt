package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.data.api.ApiService
import com.talentomobile.marvel.data.repository.data_sources_implementation.MarvelRemoteDataSourceImpl
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelRemoteDataSourceModule {

    @Singleton
    @Provides
    fun providesMarvelRemoteDataSourceModule(apiService: ApiService): MarvelRemoteDataSource {
        return MarvelRemoteDataSourceImpl(apiService)
    }
}