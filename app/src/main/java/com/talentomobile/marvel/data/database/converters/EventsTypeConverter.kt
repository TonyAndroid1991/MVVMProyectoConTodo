package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Events
import java.lang.reflect.Type

class EventsTypeConverter {

    @TypeConverter
    fun fromEventsToString(events: Events): String {
        val gson = Gson()
        val type = object : TypeToken<Events>() {}.type
        return gson.toJson(events, type)
    }

    @TypeConverter
    fun fromStringToEvents(string: String): Events {
        val gson = Gson()
        val type: Type = object : TypeToken<Events>() {}.type
        return gson.fromJson(string, type)
    }
}