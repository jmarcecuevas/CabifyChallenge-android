package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductDTO
import com.marcelocuevas.cabify.data.api.ProductsAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkProductsDataSource @Inject constructor(
    private val productsAPI : ProductsAPI
) : ProductsDataSource {

    override suspend fun getProducts(): ProductDTO =
        productsAPI.getProducts()

}
