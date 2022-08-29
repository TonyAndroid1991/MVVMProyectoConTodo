package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Item
import java.lang.reflect.Type


class ItemTypeConverter {

    @TypeConverter
    fun fromItemListToString(itemsList: List<Item>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Item>>() {}.type
        return gson.toJson(itemsList, type)
    }

    @TypeConverter
    fun fromStringToListItem(string: String): List<Item> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson<List<Item>>(string, type)
    }
}