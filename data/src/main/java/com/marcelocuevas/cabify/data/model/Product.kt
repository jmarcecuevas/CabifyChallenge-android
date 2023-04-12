package com.marcelocuevas.cabify.data.model

data class Product(
    val code: String,
    val name: String,
    val orderItemId: String? = null,
    val price: Double,
    val currency: String? = "€",
    val promotionDescription: String,
    val imageUrl: String,
    var quantity: Int = 0)