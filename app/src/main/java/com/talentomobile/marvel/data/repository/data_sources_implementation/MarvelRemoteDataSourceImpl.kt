package com.talentomobile.marvel.data.repository.data_sources_implementation

import com.talentomobile.marvel.data.api.ApiService
import com.talentomobile.marvel.data.models.AllCharactersResponse
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import retrofit2.Response

class MarvelRemoteDataSourceImpl(private val apiService: ApiService) : MarvelRemoteDataSource {

    override suspend fun getAllCharactersFromApi(limit: Int): Response<AllCharactersResponse> {
        return  apiService.getAllMarvelCharacters(limit)
    }
}