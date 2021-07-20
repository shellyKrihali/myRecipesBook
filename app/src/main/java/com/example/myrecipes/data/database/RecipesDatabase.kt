package com.example.myrecipes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myrecipes.data.database.entities.FavoritesEntity
import com.example.myrecipes.data.database.entities.RecipesEntity

// entities: one table, we set the name of entity class
// version, whenever the database updated we change the version

// room database builder will create a database module inside our Database inspector
@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}