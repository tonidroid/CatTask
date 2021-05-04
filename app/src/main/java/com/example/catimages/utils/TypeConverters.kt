package com.example.catimages.utils


import androidx.room.TypeConverter
import com.example.catimages.models.Breed
import com.example.catimages.models.Categories
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*



class CategoriesConverters {

    var gson = Gson()

    @TypeConverter
    fun stringToCategories(data: String?): List<Categories?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Categories?>?>() {}.type
        return gson.fromJson<List<Categories?>>(data, listType)
    }

    @TypeConverter
    fun categoriesListToString(someObjects: List<Categories?>?): String? {
        return gson.toJson(someObjects)
    }

}

class BreedsConverters {

    var gson = Gson()

    @TypeConverter
    fun stringToCategories(data: String?): List<Breed?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Breed?>?>() {}.type
        return gson.fromJson<List<Breed?>>(data, listType)
    }

    @TypeConverter
    fun categoriesListToString(someObjects: List<Breed?>?): String? {
        return gson.toJson(someObjects)
    }

}