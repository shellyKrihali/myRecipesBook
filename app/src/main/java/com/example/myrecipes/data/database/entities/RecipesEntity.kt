package com.example.myrecipes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrecipes.models.FoodRecipe
import com.example.myrecipes.util.Constants.Companion.RECIPES_TABLE
// an entity database class to save the response of the API.
// this class will be used when the database is not empty.
@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    // the table will contain one row of food recipe
    // type convertor from object to json since we cant store complex data in the database
    var foodRecipe: FoodRecipe
) {
    // whenever we fetch a new data from API we will replace the db data with new data
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}