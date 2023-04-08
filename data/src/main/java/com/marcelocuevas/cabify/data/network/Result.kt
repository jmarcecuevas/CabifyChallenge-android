package com.marcelocuevas.cabify.data.network

import com.marcelocuevas.cabify.data.api.ProductDTO
import com.marcelocuevas.cabify.data.api.ProductItemDTO

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {
    // We'll wrap our data in this 'Success'
    // class in case of success response from api
    class Success<T>(data: T) : Result<T>(data = data)

    // We'll pass error message wrapped in this 'Error'
    // class to the UI in case of failure response
    class Error<T>(val errorMessage: String) : Result<T>(message = errorMessage)

    // We'll just pass object of this Loading
    // class, just before making an api call
    class Loading<T> : Result<T>()
}

fun <T,R>Result<List<T>>.toDataModel(mapper: (T) -> R): Result<List<R>> {
     return when (this) {
        is Result.Success -> Result.Success(data?.map { mapper(it) }!!)
        is Result.Error -> Result.Error(errorMessage)
        else -> Result.Loading()
    }
}

fun Result<ProductDTO>.toListResult(): Result<List<ProductItemDTO>> {
    return when (this) {
        is Result.Success -> Result.Success(data?.products!!)
        is Result.Error -> Result.Error(errorMessage)
        else -> Result.Loading()
    }
}