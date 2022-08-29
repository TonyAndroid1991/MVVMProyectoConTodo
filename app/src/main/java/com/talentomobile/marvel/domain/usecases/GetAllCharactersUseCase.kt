package com.talentomobile.marvel.domain.usecases

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class GetAllCharactersUseCase(private val repository: MarvelRepository) {

    suspend fun getAllCharacters(limit: Int): Resource<List<MarvelCharacter>> {
        return repository.getAllCharacters(limit)
    }
}