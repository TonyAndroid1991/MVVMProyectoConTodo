package com.talentomobile.marvel.presentation.di

import android.app.Application
import com.talentomobile.marvel.domain.usecases.GetAllCharactersUseCase
import com.talentomobile.marvel.domain.usecases.GetCharacterFromNameUseCase
import com.talentomobile.marvel.domain.usecases.GetCharactersFromDataBaseUseCase
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
    fun providesHomeViewModuleFactory(
        getAllCharactersUseCase: GetAllCharactersUseCase,
        getCharactersFromDataBaseUseCase: GetCharactersFromDataBaseUseCase,
        getCharacterFromNameUseCase: GetCharacterFromNameUseCase,
        context: Application
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            getAllCharactersUseCase,
            getCharactersFromDataBaseUseCase,
            getCharacterFromNameUseCase,
            context
        )
    }
}