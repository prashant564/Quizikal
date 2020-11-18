package com.prashD.quizzio.network

// exposing network status
sealed class Resource<out T>(val data: T? = null,
                             val message: String? = null
) {
        class Success<T>(data:T) : Resource<T>(data)
        class Failure<T>(message: String, data: T? = null) : Resource<T>(data, message)
        class Loading<T> : Resource<T>()
}