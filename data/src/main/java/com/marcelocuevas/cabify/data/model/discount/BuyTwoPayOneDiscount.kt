package com.marcelocuevas.cabify.data.model.discount

class BuyTwoPayOneDiscount: DiscountStrategy {

    override fun applyDiscount(quantity: Int, unitPrice: Double): Double {
        if (quantity > 1) {
            val unitsToBeCharged = if (quantity % 2 == 0) {
                quantity/2
            } else {
                (quantity/2) + 1
            }
            return unitsToBeCharged * unitPrice
        }
        return quantity * unitPrice
    }
}
