package com.marcelocuevas.cabify.data.model.discount

class BuyThreeOrMoreAndGetDiscount: DiscountStrategy {

    companion object {
        private const val MIN_AMOUNT_TO_DISCOUNT = 3
    }

    override fun applyDiscount(quantity: Int, unitPrice: Double): Double {
        if (quantity > MIN_AMOUNT_TO_DISCOUNT) {
            return ((unitPrice * 5)/100) * quantity
        }
        return unitPrice * quantity
    }
}
