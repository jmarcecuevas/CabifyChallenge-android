package com.marcelocuevas.cabify.utils

import java.text.NumberFormat
import java.util.Currency

private const val CURRENCY_CODE = "EUR"
private const val MAX_FRACTION_DIGITS = 2

fun formatPrice(price: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.currency = Currency.getInstance(CURRENCY_CODE)
    numberFormat.maximumFractionDigits = MAX_FRACTION_DIGITS
    return numberFormat.format(price)
}
