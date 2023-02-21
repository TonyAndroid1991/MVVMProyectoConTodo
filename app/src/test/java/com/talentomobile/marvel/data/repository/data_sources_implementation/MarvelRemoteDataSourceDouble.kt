package com.talentomobile.marvel.data.repository.data_sources_implementation

import com.talentomobile.marvel.data.models.AllCharactersResponse
import com.talentomobile.marvel.data.repository.datas_sources.MarvelRemoteDataSource
import com.talentomobile.marvel.presentation.utils.TestUtils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class MarvelRemoteDataSourceDouble : MarvelRemoteDataSource {

    var isSuccessFull = false

    override suspend fun getAllCharactersFromApi(): Response<AllCharactersResponse> {
       return if (isSuccessFull) {
            Response.success(TestUtils().allCharactersResponse)
        } else {
           Response.error(
               400,
               "{\"key\":[\"somestuff\"]}"
                   .toResponseBody("application/json".toMediaTypeOrNull())
           )
       }
    }

    override suspend fun getCharacterFromName(name: String): Response<AllCharactersResponse> {
        return Response.success(TestUtils().allCharactersResponse)
    }
}