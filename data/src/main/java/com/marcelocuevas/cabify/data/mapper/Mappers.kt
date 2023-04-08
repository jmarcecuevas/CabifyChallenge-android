package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product

fun makeProductDtoMapper(): (ProductItemDTO) -> Product = { itemDto ->
    mapProductDto(itemDto)
}

fun <I, O> mapList(input: List<I>, mapListItem: (I) -> O): List<O> {
    return input.map { mapListItem(it) }
}

fun Float?.orZero(): Float = this ?: 0f