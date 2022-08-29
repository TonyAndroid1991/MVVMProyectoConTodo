package com.talentomobile.marvel.domain.repository

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource

interface MarvelRepository {

    suspend fun getAllCharacters(limit: Int): Resource<List<MarvelCharacter>>
}