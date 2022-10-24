package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.repository.datas_sources.MarvelLocalDataSource
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class MarvelRepositoryImpl(
    private val marvelRemoteDataSource: MarvelRemoteDataSource,
    private val marvelLocalDataSource: MarvelLocalDataSource,
) : MarvelRepository {

    override suspend fun getAllCharacters(): Resource<List<MarvelCharacter>> {
        return getAllCharactersFromApi()
    }

     override suspend fun getCharactersFromDataBase(): List<MarvelCharacter> {
       return marvelLocalDataSource.getAllCharactersFromDB()
    }

    override suspend fun getCharacterFromName(name: String): Resource<MarvelCharacter> {
        return getCharacterFromApi(name)
    }

    private suspend fun getCharacterFromApi(name: String): Resource<MarvelCharacter> {
        val response = marvelRemoteDataSource.getCharacterFromName(name)
        if (response.isSuccessful) {
            response.body()?.data?.let { characterResponse ->
                if (!characterResponse.marvelCharacters.isNullOrEmpty()) {
                    return Resource.Success(characterResponse.marvelCharacters.first())
                }
            }
        }
        return Resource.Error()
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