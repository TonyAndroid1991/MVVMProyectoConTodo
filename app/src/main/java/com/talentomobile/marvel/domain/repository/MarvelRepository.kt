package com.talentomobile.marvel.domain.repository

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource

interface MarvelRepository {
    suspend fun getAllCharacters(): Resource<List<MarvelCharacter>>
    suspend fun getCharactersFromDataBase(): List<MarvelCharacter>
    suspend fun getCharacterFromName(name: String): Resource<MarvelCharacter>
}