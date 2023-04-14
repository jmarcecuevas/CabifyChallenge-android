package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderProductsDao {

    @Upsert
    fun upsertOrder(orderItem: OrderItemEntity)

    @Transaction
    @Query("SELECT *, (i.quantity * p.price) as subtotal " +
            "FROM order_item i INNER JOIN product p " +
            "ON p.productId == i.orderItemId")
    fun getAllOrderItems(): Flow<List<OrderItemAndProduct>>

    @Query("DELETE FROM order_item WHERE orderItemId = :code")
    fun deleteOrderItem(code: String)

    @Transaction
    fun upsertOrderAndUpdateProductTransaction(orderItem: OrderItemEntity) {
        val orderId = orderItem.productId
        upsertOrder(orderItem)
        updateOrderParentInProduct(orderId, orderId)
        updateQuantityInProduct(orderId, orderItem.quantity)
    }

    @Transaction
    fun deleteOrderAndUpdateProductTransaction(code: String) {
        deleteOrderItem(code)
        updateOrderParentInProduct(code, null)
    }

    @Query("UPDATE product SET orderOwnerItemId = :orderItemId " +
            "WHERE productId = :productCode")
    fun updateOrderParentInProduct(productCode: String, orderItemId: String?)

    @Query("UPDATE product SET quantity = :quantity " +
            "WHERE productId = :productCode")
    fun updateQuantityInProduct(productCode: String, quantity: Int)

}
