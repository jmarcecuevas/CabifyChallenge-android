package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProducts(): Flow<List<Product>>

    suspend fun refreshProducts()
}
