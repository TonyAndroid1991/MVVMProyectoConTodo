package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Thumbnail
import java.lang.reflect.Type


class ThumbnailTypeConverter {


    @TypeConverter
    fun fromThumbNail(thumbNail: Thumbnail): String {

        val gson = Gson()
        val type = object : TypeToken<Thumbnail>() {}.type
        return gson.toJson(thumbNail, type)
    }

    @TypeConverter
    fun fromStringToThumbNail(string: String): Thumbnail {
        val gson = Gson()
        val type: Type = object : TypeToken<Thumbnail>() {}.type
        return gson.fromJson(string, type)
    }
}