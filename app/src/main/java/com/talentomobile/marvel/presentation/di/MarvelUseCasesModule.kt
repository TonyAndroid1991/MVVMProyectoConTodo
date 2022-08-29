package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.domain.repository.MarvelRepository
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.domain.usecases.GetCharactersFromDataBaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MarvelUseCasesModule {

    @Singleton
    @Provides
    fun providesGetAllCharactersUseCase(marvelRepository: MarvelRepository): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(marvelRepository)
    }

    @Singleton
    @Provides
    fun providesGetCharactersFromDataBaseUseCase(marvelRepository: MarvelRepository): GetCharactersFromDataBaseUseCase {
        return GetCharactersFromDataBaseUseCase(marvelRepository)
    }
}