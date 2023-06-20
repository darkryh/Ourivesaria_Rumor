package com.ead.project.ourivesariarumor.data.util

import androidx.room.TypeConverter
import com.ead.project.ourivesariarumor.domain.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {

    private val gson : Gson = Gson()

    @TypeConverter
    fun listStringToJson(value: List<String>): String = gson.toJson(value)

    @TypeConverter
    fun jsonStringToList(listOfString: String?): MutableList<String?>? = gson
        .fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun stringToTimeCategory(json : String): List<Category> = gson.fromJson(json,object : TypeToken<List<Category?>?>() {}.type)

    @TypeConverter
    fun categoryToJson(categories : List<Category>): String? = gson.toJson(categories)
}