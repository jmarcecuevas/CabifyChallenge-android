package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getProducts(): Flow<List<Product>>

    fun refreshProducts(): Flow<List<Product>>
}
