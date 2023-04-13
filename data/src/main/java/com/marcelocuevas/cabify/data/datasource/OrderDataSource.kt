package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import kotlinx.coroutines.flow.Flow

interface OrderDataSource {

      suspend fun upsertOrderItem(item: OrderItem)

      fun getOrderItems(): Flow<List<OrderItemAndProduct>>

      suspend fun deleteOrderItem(code: String)
}
