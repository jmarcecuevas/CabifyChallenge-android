package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.mapper.toDataModel
import com.marcelocuevas.cabify.framework.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
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

    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO) {
            productsDao.productsCount() <= 0
        }
    }

    override suspend fun insertProductsIfNotExist(products: List<Product>) {
        withContext(Dispatchers.IO) {
            productsDao.insertProductsIfNotExist(products.map { it.toEntity() })
        }
    }

    override suspend fun updateOrderIdInProduct(productCode: String, orderItemId: String) {
        withContext(Dispatchers.IO) {
            productsDao.updateOrderIdInProduct(productCode, orderItemId)
        }
    }
}
