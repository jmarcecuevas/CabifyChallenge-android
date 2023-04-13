package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import kotlinx.coroutines.flow.Flow


interface CartRepository {

      suspend fun upsertOrderItem(productId: String, quantity: Int)

      fun getOrderItems(): Flow<List<OrderItemAndProduct>>

      suspend fun deleteOrderItem(code: String)
}
