package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrdersRepositoryImp @Inject constructor(
    private val orderDataSource: OrderDataSource,
    private val discountCalculator: DiscountCalculator
): OrdersRepository {

    override suspend fun upsertOrderItem(productId: String, quantity: Int) {
        val orderItem = OrderItem(
            productId = productId,
            quantity = quantity
        )
        orderDataSource.upsertOrderItem(orderItem)
    }

    override fun getOrderItems(): Flow<List<OrderItemAndProduct>> {
        return orderDataSource.getOrderItems().map {
            it.map { order ->
                val finalPrice = discountCalculator.applyDiscount(order.product)
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
