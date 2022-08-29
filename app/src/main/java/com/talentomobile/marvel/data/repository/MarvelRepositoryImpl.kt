package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.repository.datas_sources.MarvelLocalDataSource
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class MarvelRepositoryImpl(
    private val marvelRemoteDataSource: MarvelRemoteDataSource,
    private val marvelLocalDataSource: MarvelLocalDataSource
) : MarvelRepository {

    override suspend fun getAllCharacters(): Resource<List<MarvelCharacter>> {
        return getAllCharactersFromApi()
    }

     override suspend fun getCharactersFromDataBase(): List<MarvelCharacter> {
       return marvelLocalDataSource.getAllCharactersFromDB()
    }

    private suspend fun getAllCharactersFromApi(): Resource<List<MarvelCharacter>> {
        val response = marvelRemoteDataSource.getAllCharactersFromApi()
        if (response.isSuccessful) {
            response.body()?.let { allCharacters ->
                marvelLocalDataSource.saveCharactersToDB(allCharacters.data.marvelCharacters)
                return Resource.Success(allCharacters.data.marvelCharacters)
            }
        }
        return Resource.Error()
    }
}