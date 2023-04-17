package com.marcelocuevas.cabify.data.datasource.impl

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.api.ProductsAPI
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import java.io.IOException
import javax.inject.Inject

class RemoteProductsDataSource @Inject constructor(
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
