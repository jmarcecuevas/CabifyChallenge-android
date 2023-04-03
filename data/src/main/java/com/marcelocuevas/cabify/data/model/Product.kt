package com.marcelocuevas.cabify.data.model

data class Product(
    val code: String,
    val name: String,
    val price: String,
    val priceWithCurrency: String,
    val currency: String? = "€",
    val promotionDescription: String,
    val imageUrl: String)