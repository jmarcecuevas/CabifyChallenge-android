package com.marcelocuevas.cabify.data.model

data class Product(
    val code: String,
    val name: String,
    val orderItemId: String? = null,
    val price: Double,
    var quantity: Int = 0)