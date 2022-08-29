package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Stories
import java.lang.reflect.Type

class StoriesTypeConverter {

    @TypeConverter
    fun fromStoriesToString(stories: Stories): String {
        val gson = Gson()
        val type: Type = object : TypeToken<Stories>() {}.type
        return gson.toJson(stories, type)
    }

    @TypeConverter
    fun fromStringToListItem(string: String): Stories {
        val gson = Gson()
        val type: Type = object : TypeToken<Stories>() {}.type
        return gson.fromJson(string, type)
    }
}