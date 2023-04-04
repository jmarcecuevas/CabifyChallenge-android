package com.marcelocuevas.cabify.framework.mapper

import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.room.ProductEntity

fun Product.asEntity() = ProductEntity(
    code = code,
    name = name,
    price = price,
    priceWithCurrency = priceWithCurrency,
    currency = currency,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)