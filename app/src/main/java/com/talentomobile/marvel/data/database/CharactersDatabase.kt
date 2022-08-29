package com.talentomobile.marvel.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.talentomobile.marvel.data.database.converters.*
import com.talentomobile.marvel.data.models.MarvelCharacter

@Database(entities = [MarvelCharacter::class], version = 1)
@TypeConverters(
    ThumbnailTypeConverter::class,
    ItemTypeConverter::class,
    ComicsTypeConverter::class,
    EventsTypeConverter::class,
    SeriesTypeConverter::class,
    ItemXXXTypeConverter::class,
    StoriesTypeConverter::class,
)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun marvelCharactersDao(): MarvelCharactersDAO
}