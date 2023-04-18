package com.marcelocuevas.cabify.data.model

import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DiscountCalculatorTest {

    private lateinit var calculator: DiscountCalculator

    @Before
    fun setup() {
        calculator = DiscountCalculator()
    }

    @Test
    fun `when applyDiscount is called with a product code without discount should return price times quantity`() {
        val expected = 15.0
        val actual = calculator.applyDiscount(
            code = "UNKNOWN",
            quantity = 3,
            unitPrice = 5.0
        )

        Assert.assertEquals(expected, actual, 0.0)
    }
}
