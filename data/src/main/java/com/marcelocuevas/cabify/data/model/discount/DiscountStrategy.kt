package com.marcelocuevas.cabify.data.model.discount

interface DiscountStrategy {

    fun applyDiscount(amount: Int, unitPrice: Double): Double
}
