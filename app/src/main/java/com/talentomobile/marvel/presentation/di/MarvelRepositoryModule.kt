package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.data.repository.MarvelRepositoryImpl
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.domain.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelRepositoryModule {

    @Singleton
    @Provides
    fun providesMarvelRepositoryModule(marvelRemoteDataSource: MarvelRemoteDataSource): MarvelRepository {
        return MarvelRepositoryImpl(marvelRemoteDataSource)
    }
}