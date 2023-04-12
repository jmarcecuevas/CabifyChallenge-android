package com.marcelocuevas.cabify.data.model

data class OrderItem(
    val productId: String,
    var quantity: Int,
    var subtotal: Double? = 0.0,
)
