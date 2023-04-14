package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow

interface LocalProductsDataSource {

    fun getProductsStream(): Flow<List<Product>>

    suspend fun isEmpty(): Boolean

    suspend fun upsertProducts(products: List<Product>)

    suspend fun insertProductsIfNotExist(products: List<Product>)

    suspend fun updateOrderIdInProduct(productCode: String, orderItemId: String)
}
