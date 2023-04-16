package com.marcelocuevas.cabify.data.model

data class OrderItemAndProduct(
    val orderItem: OrderItem,
    val product: Product?,
    val subtotal: Double,
    val total: Double,
    val discountObtained: Double,
    val hasDiscount: Boolean
)
