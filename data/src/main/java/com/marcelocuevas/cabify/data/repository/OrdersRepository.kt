package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {
      fun getOrderItems(): Flow<List<OrderItemAndProduct>>

      suspend fun upsertOrderItem(orderId: String, quantity: Int)

      suspend fun deleteOrderItem(code: String)
}
