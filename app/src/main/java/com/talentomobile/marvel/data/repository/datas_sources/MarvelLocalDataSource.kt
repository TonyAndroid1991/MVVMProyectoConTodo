package com.talentomobile.marvel.data.repository.datas_sources

import com.talentomobile.marvel.data.models.MarvelCharacter

interface MarvelLocalDataSource {
    suspend fun saveCharactersToDB(marvelCharacters: List<MarvelCharacter>)
    suspend fun getAllCharactersFromDB(): List<MarvelCharacter>
}