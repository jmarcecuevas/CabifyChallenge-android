package com.marcelocuevas.cabify.fakerepository

import android.util.Log
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import com.marcelocuevas.cabify.data.repository.OrdersRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class TestOrdersRepository : OrdersRepository {

    private val ordersFlow: MutableSharedFlow<List<OrderItemAndProduct>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getOrderItems(): Flow<List<OrderItemAndProduct>> {
        val discountCalculator = DiscountCalculator()
        return ordersFlow.map {
            it.map { order ->
                val finalPrice = discountCalculator
                    .applyDiscount(
                        code = order.product.code,
                        quantity = order.product.quantity,
                        unitPrice = order.product.price
                    )
                order.copy(
                    total = finalPrice,
                    discountObtained = order.subtotal - finalPrice,
                    hasDiscount = (order.subtotal - finalPrice) != 0.0
                )
            }
        }
    }

    override suspend fun upsertOrderItem(orderId: String, quantity: Int) {
        Log.i(javaClass.name, "upsertOrderItem called from test environment")
    }

    override suspend fun deleteOrderItem(code: String) {
        Log.i(javaClass.name, "deleteOrderItem called from test environment")
    }

    override suspend fun deleteAllOrderItems() {
        Log.i(javaClass.name, "deleteAllOrderItems called from test environment")
    }

    fun sendOrderItems(orderItems: List<OrderItemAndProduct>) {
        ordersFlow.tryEmit(orderItems)
    }
}