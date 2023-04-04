package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.mapper.Mapper
import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val networkDataSource: ProductsDataSource,
    private val localDataSource: LocalProductsDataSource,
    private val mapper: Mapper
): ProductsRepository {

    override fun getProducts(): Flow<List<Product>> {
        return localDataSource.getProductsStream()
            .onEach {
                if (it.isEmpty()) {
                    refreshProducts()
                }
            }
    }

    override fun refreshProducts(): Flow<List<Product>> {
        return networkDataSource.getProducts()
            .map {
                mapper.map(it)
//            }
//            .onEach {
//                localDataSource.deleteAndInsert(it)
            }
    }
}
