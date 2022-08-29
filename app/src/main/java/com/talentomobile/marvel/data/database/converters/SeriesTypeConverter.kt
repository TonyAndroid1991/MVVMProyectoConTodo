package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Series
import java.lang.reflect.Type

class SeriesTypeConverter {

    @TypeConverter
    fun fromSeriesToString(series: Series): String {
        val gson = Gson()
        val type = object : TypeToken<Series>() {}.type
        return gson.toJson(series, type)
    }

    @TypeConverter
    fun fromStringToSeries(string: String): Series {
        val gson = Gson()
        val type: Type = object : TypeToken<Series>() {}.type
        return gson.fromJson(string, type)
    }
}