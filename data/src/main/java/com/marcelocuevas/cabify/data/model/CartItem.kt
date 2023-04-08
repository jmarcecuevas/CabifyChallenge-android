package com.marcelocuevas.cabify.data.model

data class CartItem(
    val id: Int,
    val code: String,
    val name: String,
    val price: String,
    val currency: String = "€",
    val imageUrl: String,
    val quantity: Int,
    val totalItemPrice: Double
)



