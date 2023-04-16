package com.marcelocuevas.cabify.utils

import java.text.NumberFormat
import java.util.Currency

fun formatPrice(price: Double): String {
    val numberFormat = NumberFormat.getCurrencyInstance()
    numberFormat.currency = Currency.getInstance("EUR")
    numberFormat.maximumFractionDigits = 2
    return numberFormat.format(price)
}
