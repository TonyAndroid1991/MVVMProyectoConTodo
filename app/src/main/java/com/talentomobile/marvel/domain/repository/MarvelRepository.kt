package com.talentomobile.marvel.domain.repository

import com.talentomobile.marvel.data.models.AllCharacters
import com.talentomobile.marvel.data.utils.Resource

interface MarvelRepository {

    suspend fun getAllCharacters(): Resource<AllCharacters>
}