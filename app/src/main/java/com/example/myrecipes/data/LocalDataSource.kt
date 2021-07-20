package com.example.myrecipes.data

import com.example.myrecipes.data.database.RecipesDao
import com.example.myrecipes.data.database.entities.FavoritesEntity
import com.example.myrecipes.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// we inject this local data source to the Repository class
// we use this local data source to catch the recipes fom api to a db to access these recipes when offline
// these functions call the recipesDAO functions to manipulate data with database
class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    // we should change to live data instead of livedata

    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }

}