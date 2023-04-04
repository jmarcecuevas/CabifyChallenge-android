package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.mapper.asEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomProductsDataSource(
    private val productsDao: ProductDao
): LocalProductsDataSource {

    override fun getProductsStream(): Flow<List<Product>> {
        return productsDao.getProductsStream().map { entityProducts ->
            entityProducts.map(ProductEntity::asExternalModel)
        }
    }

    override suspend fun deleteProducts() =
        productsDao.deleteProducts()

    override suspend fun insertOrIgnoreProduct(products: List<Product>): List<Long> {
        val entities = products.map { it.asEntity() }
        return productsDao.insertOrIgnoreProducts(entities)
    }

    override suspend fun deleteAndInsert(products: List<Product>) {
        val entities = products.map { it.asEntity() }
        return productsDao.deleteAndInsert(entities)
    }
}
