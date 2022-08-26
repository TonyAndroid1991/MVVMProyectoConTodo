package com.talentomobile.marvel.data.api

import com.talentomobile.marvel.BuildConfig
import com.talentomobile.marvel.data.models.AllCharacters
import com.talentomobile.marvel.data.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp
import javax.crypto.Cipher.PUBLIC_KEY

interface ApiService {

    @GET("characters")
    suspend fun getAllMarvelCharacters(
        @Query("limit") limit: Int,
        @Query("apikey") api_key: String = BuildConfig.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String = Constants.OFFSET): Response<AllCharacters>

}