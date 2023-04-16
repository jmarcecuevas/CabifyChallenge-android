package com.marcelocuevas.cabify.data.model.discount

class BuyTwoPayOneDiscount: DiscountStrategy {

    override fun applyDiscount(quantity: Int, unitPrice: Double): Double {
        if (quantity != 0) {
            val unitsToBeCharged = (quantity / 2) + 1
            return unitsToBeCharged * unitPrice
        }
        return 0.0
    }
}
