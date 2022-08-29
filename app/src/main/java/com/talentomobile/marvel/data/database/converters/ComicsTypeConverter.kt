package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Comics
import java.lang.reflect.Type


class ComicsTypeConverter {

    @TypeConverter
    fun fromComicToString(comics: Comics): String {
        val gson = Gson()
        val type = object : TypeToken<Comics>() {}.type
        return gson.toJson(comics, type)
    }

    @TypeConverter
    fun fromStringToComics(string: String): Comics {
        val gson = Gson()
        val type: Type = object : TypeToken<Comics>() {}.type
        return gson.fromJson(string, type)
    }
}