package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem

class CartRepositoryImp(
    private val productDataSource: LocalProductsDataSource,
    private val orderDataSource: OrderDataSource
): CartRepository {

    override suspend fun upsertOrderItem(productId: String, quantity: Int) {
        val orderItem = OrderItem(
            productId = productId,
            quantity = quantity
        )
        orderDataSource.upsertOrderItem(orderItem)
        productDataSource.updateOrderIdInProduct(productId, productId)
    }
}
