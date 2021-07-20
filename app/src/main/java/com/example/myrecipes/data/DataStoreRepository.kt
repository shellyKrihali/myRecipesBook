package com.example.myrecipes.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.example.myrecipes.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.myrecipes.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.example.myrecipes.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.example.myrecipes.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

// data store running in background thread and not main thread (sp difrreneces)
// for bottom sheet choices
// reason why using this is becuz this repository gonna be used inside the recipes module
@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        // meal type is the key
        //define how to store value
        val selectedMealType = preferencesKey<String>(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = preferencesKey<Int>(PREFERENCES_MEAL_TYPE_ID)
    }

    // stores data - in a safe way
    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    // take values and store inside datastore prefernces
    // saving meal types
    suspend fun saveMealType(
        mealType: String,
        mealTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
        }
    }

    // var for reading data that we storing
    // flow corutins - when reading values from bottom sheets we use flow to pass the selected values
    // catch exception then emit empty prefernces
    // if no dta returned then we use default values
    val readMealType: Flow<MealType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
            MealType(
                selectedMealType,
                selectedMealTypeId
            )
        }
}

data class MealType(
    val selectedMealType: String,
    val selectedMealTypeId: Int
)