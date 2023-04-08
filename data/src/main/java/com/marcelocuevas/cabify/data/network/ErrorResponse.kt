package com.marcelocuevas.cabify.data.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    val status: String,
    @SerializedName("failure_message")
    val failureMessage: String
)
