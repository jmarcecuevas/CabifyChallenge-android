package com.marcelocuevas.cabify.data.model

import com.marcelocuevas.cabify.data.model.discount.BuyTwoPayOneDiscount
import com.marcelocuevas.cabify.data.model.discount.DiscountStrategy
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BuyTwoPayOneDiscountTest {

    private lateinit var discountStrategy: DiscountStrategy

    @Before
    fun setup() {
        discountStrategy = BuyTwoPayOneDiscount()
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
    fun `when applyDiscount is called with even quantity should return half the full price`() {
        val price = 1.0
        val quantity = 10
        val expected = (price * quantity)/2

        val actual = discountStrategy.applyDiscount(
            quantity = quantity,
            unitPrice = price
        )

        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `when applyDiscount is called with odd quantity distinct from one should return the right price`() {
        val price = 1.0
        val quantity = 3
        val expected = 2.0

        val actual = discountStrategy.applyDiscount(
            quantity = quantity,
            unitPrice = price
        )

        assertEquals(expected, actual, 0.0)
    }

    @Test
    fun `when applyDiscount is called with quantity one should return one times unitPrice `() {
        val price = 2.0
        val quantity = 1
        val expected = 2.0

        val actual = discountStrategy.applyDiscount(
            quantity = quantity,
            unitPrice = price
        )

        assertEquals(expected, actual, 0.0)
    }
}
