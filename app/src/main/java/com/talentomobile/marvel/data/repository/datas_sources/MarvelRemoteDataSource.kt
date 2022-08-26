package com.talentomobile.marvel.data.repository.datas_sources

import com.talentomobile.marvel.data.models.AllCharacters
import retrofit2.Response

interface MarvelRemoteDataSource {
    suspend fun getAllCharactersFromApi(limit: Int): Response<AllCharacters>
}