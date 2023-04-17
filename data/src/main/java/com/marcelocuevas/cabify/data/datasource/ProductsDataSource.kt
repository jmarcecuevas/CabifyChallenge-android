package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductItemDTO

interface ProductsDataSource {

    suspend fun getProducts(): List<ProductItemDTO>
}
