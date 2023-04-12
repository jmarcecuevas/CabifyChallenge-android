package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.framework.mapper.toEntity
import com.marcelocuevas.cabify.framework.room.entity.OrderDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomOrderDataSource @Inject constructor(
    private val orderDao: OrderDao,
): OrderDataSource{

    override suspend fun upsertOrderItem(item: OrderItem): Long {
        return withContext(Dispatchers.IO) {
            orderDao.upsertOrder(item.toEntity())
        }
    }
}
