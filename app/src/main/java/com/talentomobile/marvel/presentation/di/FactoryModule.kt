package com.talentomobile.marvel.presentation.di

import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.presentation.ui.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun providesHomeViewModuleFactory(getAllCharactersUseCase: GetAllCharactersUseCase): HomeViewModelFactory {
        return HomeViewModelFactory(getAllCharactersUseCase)
    }
}