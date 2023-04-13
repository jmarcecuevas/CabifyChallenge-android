package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductDTO
import kotlinx.coroutines.flow.Flow

interface ProductsDataSource {

    suspend fun getProducts(): ProductDTO
}
