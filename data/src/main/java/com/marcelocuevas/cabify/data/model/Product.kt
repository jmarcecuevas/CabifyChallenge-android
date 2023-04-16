package com.marcelocuevas.cabify.data.model

import com.marcelocuevas.cabify.data.api.ProductCode

data class Product(
    val code: ProductCode,
    val name: String,
    val orderItemId: String? = null,
    val price: Double,
    val currency: String? = "€",
    val promotionDescription: String,
    val imageUrl: String,
    var quantity: Int = 0)