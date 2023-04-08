package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow

interface LocalProductsDataSource {

    fun getProductsStream(): Flow<List<Product>>

    suspend fun saveProducts(products: List<Product>)
}
