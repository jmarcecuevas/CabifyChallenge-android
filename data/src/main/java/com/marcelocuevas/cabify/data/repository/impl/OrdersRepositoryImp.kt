package com.marcelocuevas.cabify.data.repository.impl

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import com.marcelocuevas.cabify.data.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrdersRepositoryImp @Inject constructor(
    private val orderDataSource: OrderDataSource,
    private val discountCalculator: DiscountCalculator
): OrdersRepository {

    override suspend fun upsertOrderItem(orderId: String, quantity: Int) {
        val orderItem = OrderItem(
            productId = orderId,
            quantity = quantity
        )
        orderDataSource.upsertOrderItem(orderItem)
    }

    override fun getOrderItems(): Flow<List<OrderItemAndProduct>> {
        return orderDataSource.getOrderItemsStream().map {
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

    override suspend fun deleteOrderItem(code: String) {
        orderDataSource.deleteOrderItem(code)
    }
}
