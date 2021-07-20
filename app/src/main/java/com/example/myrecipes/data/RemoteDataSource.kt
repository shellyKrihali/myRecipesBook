package com.example.myrecipes.data

import com.example.myrecipes.data.network.FoodRecipesApi
import com.example.myrecipes.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

// request data from API - we inject food recipe inside it so we can request new data from API
// two different data soruces: remote(api) and local (local database)
// were gona inject foodrecipes API
// after injecting the hilt will know how to search for a function that creates this module in networkModule
// we create a local data source and inject the recipe dao inside it
class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

}