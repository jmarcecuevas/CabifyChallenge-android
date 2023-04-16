package com.marcelocuevas.cabify.data.model.discount

interface DiscountStrategy {

    fun applyDiscount(quantity: Int, unitPrice: Double): Double
}
