package com.marcelocuevas.cabify.data.model.discount

import com.marcelocuevas.cabify.data.api.ProductCode
import javax.inject.Inject

class DiscountCalculator @Inject constructor() {

    fun applyDiscount(code: String, quantity: Int, unitPrice: Double): Double {
        return when (code) {
            ProductCode.VOUCHER.name -> {
                BuyTwoPayOneDiscount().applyDiscount(
                    quantity = quantity,
                    unitPrice = unitPrice
                )
            }
            ProductCode.TSHIRT.name -> {
                BuyXOrMoreAndGetDiscount(
                    minAmountToDiscount = 3,
                    priceWithDiscount = 19.00
                ).applyDiscount(quantity, unitPrice)
            } else -> {
                unitPrice * quantity
            }
        }
    }
}
