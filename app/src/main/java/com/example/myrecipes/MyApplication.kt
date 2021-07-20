package com.example.myrecipes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
// network model to tell hilt to inject retrofit instance
// tell hilt to provide a retrofit builder to out datasource

// dependency injection library
// Dependency injection is a programming technique that makes a class independent of its dependencies
// All apps that use Hilt must contain an Application class that is annotated with @HiltAndroidApp.
//
// @HiltAndroidApp triggers Hilt's code generation, including a base class for your application that serves as the application-level dependency container.
// This generated Hilt component is attached to the Application object's lifecycle and provides dependencies to it.
// Additionally, it is the parent component of the app, which means that other components can access the dependencies that it provides.
@HiltAndroidApp
class MyApplication: Application() {
}