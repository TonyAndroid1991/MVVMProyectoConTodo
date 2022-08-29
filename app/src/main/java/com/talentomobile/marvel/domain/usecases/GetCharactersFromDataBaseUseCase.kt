package com.talentomobile.marvel.domain.usecases

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.domain.repository.MarvelRepository

class GetCharactersFromDataBaseUseCase(private val repository: MarvelRepository) {

    suspend fun getAllCharactersFromDataBase(): List<MarvelCharacter> {
        return repository.getCharactersFromDataBase()
    }
}