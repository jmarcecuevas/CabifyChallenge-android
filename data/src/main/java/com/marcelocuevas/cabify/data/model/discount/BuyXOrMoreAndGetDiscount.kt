package com.marcelocuevas.cabify.data.model.discount

class BuyXOrMoreAndGetDiscount(
    private val minAmountToDiscount: Int,
    private val priceWithDiscount: Double
): DiscountStrategy {

    override fun applyDiscount(quantity: Int, unitPrice: Double): Double {
        if (quantity >= minAmountToDiscount) {
            return quantity * priceWithDiscount
        }
        return unitPrice * quantity
    }
}
