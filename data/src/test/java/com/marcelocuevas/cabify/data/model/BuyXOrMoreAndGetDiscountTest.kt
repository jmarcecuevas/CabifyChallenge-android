package com.marcelocuevas.cabify.data.model

import com.marcelocuevas.cabify.data.model.discount.BuyXOrMoreAndGetDiscount
import com.marcelocuevas.cabify.data.model.discount.DiscountStrategy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BuyXOrMoreAndGetDiscountTest {

    companion object {
        private const val MIN_AMOUNT_TO_DISCOUNT = 3
        private const val PRICE_WITH_DISCOUNT = 19.0
    }

    private lateinit var discountStrategy: DiscountStrategy

    @Before
    fun setup() {
        discountStrategy = BuyXOrMoreAndGetDiscount(
            minAmountToDiscount = MIN_AMOUNT_TO_DISCOUNT,
            priceWithDiscount = PRICE_WITH_DISCOUNT
        )
    }

    @Test
    fun `when applyDiscount is called with quantity 0 should return 0`() {
        val expected = 0.0
        val actual = discountStrategy.applyDiscount(
            quantity = 0,
            unitPrice = 5.0
        )

        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `when applyDiscount is called with quantity equals to MIN_AMOUNT_TO_DISCOUNT should use the price with discount`() {
        val expected = MIN_AMOUNT_TO_DISCOUNT * PRICE_WITH_DISCOUNT
        val actual = discountStrategy.applyDiscount(
            quantity = MIN_AMOUNT_TO_DISCOUNT,
            unitPrice = 20.0
        )

        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `when applyDiscount is called with quantity greater than MIN_AMOUNT_TO_DISCOUNT should use the price with discount`() {
        val expected = (MIN_AMOUNT_TO_DISCOUNT + 1) * PRICE_WITH_DISCOUNT
        val actual = discountStrategy.applyDiscount(
            quantity = MIN_AMOUNT_TO_DISCOUNT + 1,
            unitPrice = 20.0
        )

        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `when applyDiscount is called with quantity greater than 0 and lower than MIN_AMOUNT_TO_DISCOUNt should use the original price`() {
        val unitPrice = 20.0
        val expected = (MIN_AMOUNT_TO_DISCOUNT - 1) * unitPrice
        val actual = discountStrategy.applyDiscount(
            quantity = MIN_AMOUNT_TO_DISCOUNT - 1,
            unitPrice = unitPrice
        )

        assertEquals(expected, actual, 0.0)
    }
}
