package com.marcelocuevas.cabify.data.repository.impl

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val remoteDataSource: ProductsDataSource,
    private val localDataSource: LocalProductsDataSource,
    private val mapper: (ProductItemDTO) -> (Product)
): ProductsRepository {

    override suspend fun getProducts(): Flow<List<Product>> {
        if (localDataSource.isEmpty()) {
            refreshProducts()
        }
        return localDataSource.getProductsStream()
    }

    override suspend fun refreshProducts() {
        val products = getProductsFromRemote()
        insertProductsIfNotExist(products)
    }

    private suspend fun getProductsFromRemote(): List<Product> {
        return remoteDataSource.getProducts()
                .map { mapper(it) }
    }

    private suspend fun insertProductsIfNotExist(products: List<Product>) {
        localDataSource.insertProductsIfNotExist(products)
    }
}
