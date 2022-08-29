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

    override suspend fun getAllCharacters(limit: Int): Resource<List<MarvelCharacter>> {
        val marvelCharacters = getCharactersFromDataBase(limit)

        if (marvelCharacters.isNotEmpty()) {
            return Resource.Success(marvelCharacters)
        }
        return Resource.Error()
    }


    private suspend fun getCharactersFromDataBase(limit: Int): List<MarvelCharacter> {

        var marvelCharacters = marvelLocalDataSource.getAllCharactersFromDB()

        if (marvelCharacters.isNotEmpty()) {
            return marvelCharacters
        } else {
            marvelCharacters = getAllCharactersFromApi(limit)
            marvelLocalDataSource.saveCharactersToDB(marvelCharacters)
        }
        return marvelCharacters
    }


    private suspend fun getAllCharactersFromApi(limit: Int): List<MarvelCharacter> {
        var marvelCharacters: List<MarvelCharacter> = listOf()
        val response = marvelRemoteDataSource.getAllCharactersFromApi(limit)

        if (response.isSuccessful) {
            response.body()?.let { allCharacters ->
                marvelCharacters = allCharacters.data.marvelCharacters
            }
        }
        return marvelCharacters
    }
}