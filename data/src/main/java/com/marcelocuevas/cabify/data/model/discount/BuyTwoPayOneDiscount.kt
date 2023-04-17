package com.marcelocuevas.cabify.data.model.discount

import kotlin.math.ceil

class BuyTwoPayOneDiscount: DiscountStrategy {

    override fun applyDiscount(quantity: Int, unitPrice: Double): Double {
        val itemsToBeCharged = ceil(quantity / 2.toDouble())
        return itemsToBeCharged * unitPrice
    }
}
