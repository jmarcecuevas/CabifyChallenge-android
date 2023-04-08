package com.marcelocuevas.cabify.data.network

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Result<T> {

    // Returning api response
    // wrapped in Resource class
    return withContext(Dispatchers.IO) {
        try {

            // Here we are calling api lambda
            // function that will return response
            // wrapped in Retrofit's Response class
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                // In case of success response we
                // are returning Resource.Success object
                // by passing our data in it.
                Result.Success(data = response.body()!!)
            } else {
                // parsing api's own custom json error
                // response in ExampleErrorResponse pojo
                val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())
                // Simply returning api's own failure message
                Result.Error(errorMessage = errorResponse?.failureMessage ?: "Something went wrong")
            }

        } catch (e: HttpException) {
            // Returning HttpException's message
            // wrapped in Resource.Error
            Result.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            // Returning no internet message
            // wrapped in Resource.Error
            Result.Error("Please check your network connection")
        } catch (e: Exception) {
            // Returning 'Something went wrong' in case
            // of unknown error wrapped in Resource.Error
            Result.Error(errorMessage = "Something went wrong")
        }
    }
}

private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
    return try {
        errorBody?.charStream()?.let {
             Gson().fromJson(it, ErrorResponse::class.java)
        }
    } catch (exception: Exception) {
        null
    }
}
