package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product

fun mapProductDto(input: ProductItemDTO) = Product(
    code = input.code ?: "UNKNOWN",
    name = input.name.orEmpty(),
    price = input.price.orZero()
)
