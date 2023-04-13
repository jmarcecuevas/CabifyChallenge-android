package com.marcelocuevas.cabify.data.model.discount

class BuyTwoPayOneDiscount: DiscountStrategy {

    override fun applyDiscount(amount: Int, unitPrice: Double): Double {
        if (amount != 0) {
            val unitsToBeCharged = (amount / 2) + 1
            return unitsToBeCharged * unitPrice
        }
        return 0.0
    }
}
