package com.example.myrecipes.util
// Sealed classes and interfaces represent restricted class hierarchies that
// provide more control over inheritance. All subclasses of a sealed class are known at compile time.
// ... A sealed class is abstract by itself, it cannot be instantiated
// directly and can have abstract members.

// this network result will represent three different classes
// two params: 1. represent the actual data from the API 2. represents a message
//  T? = null -- nullable param
sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    // we use this class to parse a response from API call
    class Success<T>(data: T): NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null): NetworkResult<T>(data, message)
    class Loading<T>: NetworkResult<T>()

}