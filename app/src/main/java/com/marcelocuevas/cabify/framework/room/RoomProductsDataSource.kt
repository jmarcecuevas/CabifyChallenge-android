package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.mapper.toDataModel
import com.marcelocuevas.cabify.framework.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomProductsDataSource @Inject constructor(
    private val productsDao: ProductDao
): LocalProductsDataSource {

    override fun getProductsStream(): Flow<List<Product>> {
        return productsDao
            .getProductsStream()
            .map { entityProducts ->
                entityProducts.map { it.toDataModel() }
        }
    }

    override suspend fun saveProducts(products: List<Product>) {
        val entities = products.map { it.toEntity() }
        productsDao.saveProducts(entities)
    }
}
