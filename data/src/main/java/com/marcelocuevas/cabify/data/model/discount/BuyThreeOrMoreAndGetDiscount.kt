package com.marcelocuevas.cabify.data.model.discount

class BuyThreeOrMoreAndGetDiscount: DiscountStrategy {

    companion object {
        private const val MIN_AMOUNT_TO_DISCOUNT = 3
    }

    override fun applyDiscount(amount: Int, unitPrice: Double): Double {
        if (amount > MIN_AMOUNT_TO_DISCOUNT) {
            return ((unitPrice * 5)/100) * amount
        }
        return unitPrice * amount
    }
}