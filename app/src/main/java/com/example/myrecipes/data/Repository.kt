package com.example.myrecipes.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

// inject this Repository in a view module later
// we create a view modile class to inject this repository
@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource, // api
    localDataSource: LocalDataSource // room
) {

    val remote = remoteDataSource
    val local = localDataSource

}