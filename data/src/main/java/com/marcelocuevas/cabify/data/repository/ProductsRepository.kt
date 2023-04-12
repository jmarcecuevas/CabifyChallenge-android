package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.Result
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<Result<List<Product>>>

    fun getProductsStream(): Flow<List<Product>>

    suspend fun saveProducts(products: List<Product>)
}
