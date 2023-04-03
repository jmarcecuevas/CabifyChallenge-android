package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    fun getProducts(): Flow<List<ProductItemDTO>>
}
