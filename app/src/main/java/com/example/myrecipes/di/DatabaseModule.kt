package com.example.myrecipes.di

import android.content.Context
import androidx.room.Room
import com.example.myrecipes.data.database.RecipesDatabase
import com.example.myrecipes.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
// hilt library to provide database builder
// fun to tell hilt to provide room builder
@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {
    // fun to provide the room database builder
    // a room library is a third party class
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).build()

    // we call the abstract fun  from RecipesDatabase
    // we inject this dao to other classes
    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) = database.recipesDao()

}