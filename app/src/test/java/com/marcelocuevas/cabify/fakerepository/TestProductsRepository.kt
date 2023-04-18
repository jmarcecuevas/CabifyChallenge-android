package com.marcelocuevas.cabify.fakerepository

import android.util.Log
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestProductsRepository: ProductsRepository {

    private val productsFlow: MutableSharedFlow<List<Product>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getProducts(): Flow<List<Product>> {
        return productsFlow
    }

    override suspend fun refreshProducts() {
        productsFlow.tryEmit(emptyList())
    }

    fun sendProductsItems(products: List<Product>) {
        productsFlow.tryEmit(products)
    }
}