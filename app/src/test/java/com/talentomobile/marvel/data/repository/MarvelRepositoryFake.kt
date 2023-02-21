package com.talentomobile.marvel.data.repository

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.utils.Resource
import com.talentomobile.marvel.domain.repository.MarvelRepository
import com.talentomobile.marvel.presentation.utils.TestUtils

class MarvelRepositoryFake : MarvelRepository {

    override suspend fun getAllCharacters(): Resource<List<MarvelCharacter>> {
        return Resource.Success(TestUtils().listOfMarvelCharacter)
    }

    override suspend fun getCharactersFromDataBase(): List<MarvelCharacter> {
        return TestUtils().listOfMarvelCharacter
    }

    override suspend fun getCharacterFromName(name: String): Resource<MarvelCharacter> {
        return Resource.Success(TestUtils().marvelCharacter)
    }
}