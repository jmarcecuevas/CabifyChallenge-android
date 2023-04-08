package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductDTO
import com.marcelocuevas.cabify.data.network.Result

interface ProductsDataSource {

    suspend fun getProducts(): Result<ProductDTO>
}
