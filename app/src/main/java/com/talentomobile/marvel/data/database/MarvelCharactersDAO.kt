package com.talentomobile.marvel.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.talentomobile.marvel.data.models.MarvelCharacter

@Dao
interface MarvelCharactersDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(marvelCharacters: List<MarvelCharacter>)

    @Query("SELECT * FROM marvel_characters")
    suspend fun getAllCharacters(): List<MarvelCharacter>
}