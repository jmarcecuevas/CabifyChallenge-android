package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product

fun makeProductDtoMapper(): (ProductItemDTO) -> Product = { itemDto ->
    Product(
        code = itemDto.code ?: "UNKNOWN",
        name = itemDto.name.orEmpty(),
        price = itemDto.price.orZero()
    )
}

fun Double?.orZero(): Double = this ?: 0.0