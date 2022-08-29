package com.talentomobile.marvel.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.talentomobile.marvel.data.models.Item
import com.talentomobile.marvel.data.models.ItemXXX
import java.lang.reflect.Type

class ItemXXXTypeConverter {

    @TypeConverter
    fun fromItemXXXListToString(itemsList: List<ItemXXX>): String {
        val gson = Gson()
        val type: Type = object : TypeToken<List<ItemXXX>>() {}.type
        return gson.toJson(itemsList, type)
    }

    @TypeConverter
    fun fromStringToListItemXXX(string: String): List<ItemXXX> {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(string, type)
    }

}