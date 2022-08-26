package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.models.AllCharacters
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val marvelRemoteDataSource: MarvelRemoteDataSource) : MarvelRepository {

    override suspend fun getAllCharacters(limit: Int): Resource<AllCharacters> {
        return getAllCharactersFromApi(limit)
    }

    private suspend fun getAllCharactersFromApi(limit: Int): Resource<AllCharacters> {
        val response = marvelRemoteDataSource.getAllCharactersFromApi(limit)

        if (response.isSuccessful) {
            response?.body()?.let { allCharacters ->
                return Resource.Success(allCharacters)
            }
        }
        return Resource.Error(response.message())
    }
}