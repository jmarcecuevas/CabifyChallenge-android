package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import kotlinx.coroutines.flow.Flow

class CartRepositoryImp(
    private val orderDataSource: OrderDataSource
): CartRepository {

    override suspend fun upsertOrderItem(productId: String, quantity: Int) {
        val orderItem = OrderItem(
            productId = productId,
            quantity = quantity
        )
        orderDataSource.upsertOrderItem(orderItem)
    }

    override fun getOrderItems(): Flow<List<OrderItemAndProduct>> {
        return orderDataSource.getOrderItems()
    }

    override suspend fun deleteOrderItem(code: String) {
        orderDataSource.deleteOrderItem(code)
    }
}
