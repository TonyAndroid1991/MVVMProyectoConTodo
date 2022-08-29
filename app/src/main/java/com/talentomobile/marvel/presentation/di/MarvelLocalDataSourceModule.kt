package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.data.database.MarvelCharactersDAO
import com.talentomobile.marvel.data.repository.data_sources_implementation.MarvelLocalDataSourceImpl
import com.talentomobile.marvel.data.repository.datas_sources.MarvelLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelLocalDataSourceModule {

    @Singleton
    @Provides
    fun providesMarvelLocalDataSource(marvelCharactersDAO: MarvelCharactersDAO): MarvelLocalDataSource {
        return MarvelLocalDataSourceImpl(marvelCharactersDAO)
    }
}