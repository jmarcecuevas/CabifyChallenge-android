package com.marcelocuevas.cabify

import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.Product

internal val testOrdersList = listOf(
    OrderItemAndProduct(
        orderItem = OrderItem("VOUCHER", 1),
        product = Product("VOUCHER", "Cabify voucher", price = 5.0, quantity = 1),
        subtotal = 5.0,
        total = 5.0,
        discountObtained = 0.0,
        hasDiscount = false
    )
)

internal val testProductList = listOf(
    Product("VOUCHER", "Cabify voucher", price = 5.0, quantity = 1),
    Product("MUG", "Cabify mug", price = 10.0, quantity = 2),
)