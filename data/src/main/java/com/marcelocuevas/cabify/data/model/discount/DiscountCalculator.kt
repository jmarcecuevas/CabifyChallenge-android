package com.marcelocuevas.cabify.data.model.discount

import com.marcelocuevas.cabify.data.api.ProductCode
import com.marcelocuevas.cabify.data.model.Product
import javax.inject.Inject

class DiscountCalculator @Inject constructor() {

    fun applyDiscount(product: Product): Double {
        val quantity = product.quantity
        val unitPrice = product.price

        return when (product.code) {
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
