package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val remoteDataSource: ProductsDataSource,
    private val localDataSource: LocalProductsDataSource,
    private val mapper: (ProductItemDTO) -> (Product)
): ProductsRepository {

    override suspend fun getProducts(): Flow<List<Product>> {
        if (localDataSource.isEmpty()) {
            saveProducts(getProductsFromRemote())
        }
        return localDataSource.getProductsStream()
    }

    private suspend fun getProductsFromRemote(): List<Product> {
        return remoteDataSource.getProducts()
            .products!!.map { mapper(it) }
    }

    private suspend fun saveProducts(products: List<Product>) {
        localDataSource.saveProducts(products)
    }
}
