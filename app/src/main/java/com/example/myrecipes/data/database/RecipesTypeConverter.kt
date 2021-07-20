package com.example.myrecipes.data.database

import androidx.room.TypeConverter
import com.example.myrecipes.models.FoodRecipe
import com.example.myrecipes.models.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// type convertor class to convert from object to json, to add data to database
// cant directly store object type data to the database
class RecipesTypeConverter {

    // using Gson library to convert to Json
    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }

}