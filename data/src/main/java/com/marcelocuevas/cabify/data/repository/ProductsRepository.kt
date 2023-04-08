package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.CartItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.Result
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    suspend fun getProducts(): Flow<Result<List<Product>>>

    suspend fun getCartItems(): Flow<List<CartItem>>
}
