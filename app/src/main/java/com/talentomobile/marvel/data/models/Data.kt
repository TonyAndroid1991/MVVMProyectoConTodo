package com.talentomobile.marvel.data.models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val marvelCharacters: List<MarvelCharacter>,
    @SerializedName("total")
    val total: Int
)