package com.example.myrecipes.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.DataStoreRepository
import com.example.myrecipes.util.Constants.Companion.API_KEY
import com.example.myrecipes.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.myrecipes.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.example.myrecipes.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.myrecipes.util.Constants.Companion.QUERY_API_KEY
import com.example.myrecipes.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.myrecipes.util.Constants.Companion.QUERY_NUMBER
import com.example.myrecipes.util.Constants.Companion.QUERY_SEARCH
import com.example.myrecipes.util.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// we inject the the Data store repository inside view model
class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE

    var networkStatus = false
    // return backonline
//    var backOnline = false

    val readMealType = dataStoreRepository.readMealType
//    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData() // return backonline

    fun saveMealType(mealType: String, mealTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealType(mealType, mealTypeId)
        }

    // return backonline
//    private fun saveBackOnline(backOnline: Boolean) =
//        viewModelScope.launch(Dispatchers.IO) {
////            dataStoreRepository.saveBackOnline(backOnline)
//        }
    // manage queries to api

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            // collect values from Flow
            readMealType.collect { value ->
                mealType = value.selectedMealType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    // queries for search
    fun applySearchQuery(searchQuery: String): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
// return backonline
//    fun showNetworkStatus() {
//        if (!networkStatus) {
//            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
//            saveBackOnline(true)
//        } else if (networkStatus) {
//            if (backOnline) {
//                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
//                saveBackOnline(false)
//            }
//        }
//    }
}