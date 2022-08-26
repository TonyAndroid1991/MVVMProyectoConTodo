package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.domain.repository.MarvelRepository
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
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
}