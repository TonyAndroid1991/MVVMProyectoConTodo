package com.talentomobile.marvel.presentation.di

import android.app.Application
import androidx.room.Room
import com.talentomobile.marvel.data.database.CharactersDatabase
import com.talentomobile.marvel.data.database.MarvelCharactersDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CharactersDataBaseModule {

    @Singleton
    @Provides
    fun providesCharactersDataBase(app: Application): CharactersDatabase {
        return Room.databaseBuilder(app, CharactersDatabase::class.java, "characters_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesMarvelCharactersDAO(charactersDatabase: CharactersDatabase): MarvelCharactersDAO {
        return charactersDatabase.marvelCharactersDao()
    }
}