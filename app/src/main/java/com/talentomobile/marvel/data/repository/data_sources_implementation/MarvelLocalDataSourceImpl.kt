package com.talentomobile.marvel.data.repository.data_sources_implementation

import com.talentomobile.marvel.data.database.MarvelCharactersDAO
import com.talentomobile.marvel.data.models.MarvelCharacter
import com.talentomobile.marvel.data.repository.datas_sources.MarvelLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelLocalDataSourceImpl(private val marvelCharactersDAO: MarvelCharactersDAO) :
    MarvelLocalDataSource {

    override suspend fun saveCharactersToDB(marvelCharacters: List<MarvelCharacter>) {
        CoroutineScope(Dispatchers.IO).launch {
            marvelCharactersDAO.saveCharacters(marvelCharacters)
        }
    }

    override suspend fun getAllCharactersFromDB(): List<MarvelCharacter> {
        return marvelCharactersDAO.getAllCharacters()
    }
}