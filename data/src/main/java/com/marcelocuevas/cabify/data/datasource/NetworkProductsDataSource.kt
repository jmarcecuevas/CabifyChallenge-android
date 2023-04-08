package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductDTO
import com.marcelocuevas.cabify.data.api.ProductsAPI
import com.marcelocuevas.cabify.data.network.Result
import com.marcelocuevas.cabify.data.network.safeApiCall
import javax.inject.Inject

class NetworkProductsDataSource @Inject constructor(
    private val productsAPI : ProductsAPI
) : ProductsDataSource {

    override suspend fun getProducts(): Result<ProductDTO> =
        safeApiCall { productsAPI.getProducts() }
}
