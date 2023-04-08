package com.marcelocuevas.cabify.framework.mapper

import com.marcelocuevas.cabify.data.model.CartItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.room.CartItemEntity
import com.marcelocuevas.cabify.framework.room.ProductEntity

fun Product.toEntity() = ProductEntity(
    code = code,
    name = name,
    price = price,
    priceWithCurrency = priceWithCurrency,
    currency = currency,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)

fun ProductEntity.toDataModel() = Product(
    code = code,
    name = name,
    price = price,
    priceWithCurrency = priceWithCurrency,
    currency = currency,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)

fun CartItemEntity.toDataModel() = CartItem(
    id = id,
    code = code,
    name = name,
    price = price,
    currency = currency,
    imageUrl = imageUrl,
    quantity = quantity,
    totalItemPrice = totalItemPrice
)

fun CartItem.toEntity() = CartItemEntity(
    id = id,
    code = code,
    name = name,
    price = price,
    currency = currency,
    imageUrl = imageUrl,
    quantity = quantity,
    totalItemPrice = totalItemPrice
)
