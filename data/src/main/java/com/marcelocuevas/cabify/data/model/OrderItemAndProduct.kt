package com.marcelocuevas.cabify.data.model

data class OrderItemAndProduct(
    val orderItem: OrderItem,
    val product: Product?,
    val subtotal: Double
)
