package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import kotlinx.coroutines.flow.Flow

interface OrderDataSource {
      fun getOrderItemsStream(): Flow<List<OrderItemAndProduct>>

      suspend fun upsertOrderItem(item: OrderItem)

      suspend fun deleteOrderItem(itemId: String)
}
