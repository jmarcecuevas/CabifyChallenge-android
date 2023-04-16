package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.api.ProductsAPI
import java.io.IOException
import javax.inject.Inject

class NetworkProductsDataSource @Inject constructor(
    private val productsAPI : ProductsAPI
) : ProductsDataSource {

    override suspend fun getProducts(): List<ProductItemDTO> {
        return try {
            productsAPI.getProducts().products
        } catch (e: IOException) {
            emptyList()
        }
    }
}
