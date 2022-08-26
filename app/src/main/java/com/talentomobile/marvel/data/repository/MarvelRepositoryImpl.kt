package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.models.AllCharacters
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class MarvelRepositoryImpl(private val marvelRemoteDataSource: MarvelRemoteDataSource) : MarvelRepository {

    override suspend fun getAllCharacters(): Resource<AllCharacters> {
        return getAllCharactersFromApi()
    }

    suspend fun getAllCharactersFromApi(): Resource<AllCharacters> {
        val response = marvelRemoteDataSource.getAllCharactersFromApi()

        if (response.isSuccessful) {
            response?.body()?.let { allCharacters ->
                return Resource.Success(allCharacters)
            }
        }
        return Resource.Error(response.message())
    }
}