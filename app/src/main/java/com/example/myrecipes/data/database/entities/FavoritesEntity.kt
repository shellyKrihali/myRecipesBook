package com.example.myrecipes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrecipes.models.Result
import com.example.myrecipes.util.Constants.Companion.FAVORITE_RECIPES_TABLE

// database entity for favorites, has two cols (id and result)
// will be created locally
@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)