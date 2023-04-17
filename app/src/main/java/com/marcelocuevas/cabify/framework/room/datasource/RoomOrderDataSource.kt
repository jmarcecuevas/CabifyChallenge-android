package com.marcelocuevas.cabify.framework.room.datasource

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.mapper.toDataModel
import com.marcelocuevas.cabify.mapper.toEntity
import com.marcelocuevas.cabify.framework.room.dao.OrderProductsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomOrderDataSource @Inject constructor(
    private val orderDao: OrderProductsDao,
): OrderDataSource{

    override suspend fun upsertOrderItem(item: OrderItem) {
        return withContext(Dispatchers.IO) {
            orderDao.upsertOrderAndUpdateProductTransaction(item.toEntity())
        }
    }

    override fun getOrderItemsStream(): Flow<List<OrderItemAndProduct>> {
        return orderDao.getAllOrderItems().map { it.toDataModel() }
    }

    override suspend fun deleteOrderItem(itemId: String) {
        withContext(Dispatchers.IO) {
            orderDao.deleteOrderAndUpdateProductTransaction(itemId)
        }
    }
}
