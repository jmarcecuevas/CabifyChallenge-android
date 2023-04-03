package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun map(list: List<ProductItemDTO>): List<Product> {
        return mapListProductDTOtoListProduct(list)
    }
}
