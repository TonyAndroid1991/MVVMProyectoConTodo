package com.talentomobile.marvel.domain.usecases

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository

class GetCharacterFromNameUseCase(private val marvelRepository: MarvelRepository) {

    suspend fun getCharacterFromName(name: String): Resource<MarvelCharacter> {
        return marvelRepository.getCharacterFromName(name)
    }
}