package com.talentomobile.marvel.data.repository.data_sources_implementation

import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.repository.datas_sources.MarvelLocalDataSource
import com.talentomobile.marvel.presentation.utils.TestUtils

class MarvelLocalDataSourceDouble : MarvelLocalDataSource {

    override suspend fun saveCharactersToDB(marvelCharacters: List<MarvelCharacter>) {

    }

    override suspend fun getAllCharactersFromDB(): List<MarvelCharacter> {
        return TestUtils().listOfMarvelCharacter
    }

    fun saveToDataBase() {

    }
}