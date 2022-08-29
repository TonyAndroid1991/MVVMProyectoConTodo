package com.talentomobile.marvel.data.repository.datas_sources

import com.talentomobile.marvel.data.models.AllCharactersResponse
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun getAllCharactersFromApi(): Response<AllCharactersResponse>
}