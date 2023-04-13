package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.framework.mapper.toDataModel
import com.marcelocuevas.cabify.framework.mapper.toEntity
import com.marcelocuevas.cabify.framework.room.entity.OrderDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomOrderDataSource @Inject constructor(
    private val orderDao: OrderDao,
): OrderDataSource{

    override suspend fun upsertOrderItem(item: OrderItem) {
        return withContext(Dispatchers.IO) {
            orderDao.todo(item.toEntity())
        }
    }

    override fun getOrderItems(): Flow<List<OrderItemAndProduct>> {
        return orderDao.getAllOrderItems().map { it.toDataModel() }
    }

    override suspend fun deleteOrderItem(code: String) {
        withContext(Dispatchers.IO) {
            orderDao.algo(code)
        }
    }
}
