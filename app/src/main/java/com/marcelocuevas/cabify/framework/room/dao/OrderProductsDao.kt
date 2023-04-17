package com.marcelocuevas.cabify.framework.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.marcelocuevas.cabify.framework.room.entity.OrderItemAndProduct
import com.marcelocuevas.cabify.framework.room.entity.OrderItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderProductsDao {
    @Transaction
    @Query("SELECT *, (i.quantity * p.price) as subtotal " +
            "FROM order_item i INNER JOIN product p " +
            "ON p.productId == i.orderItemId")
    fun getAllOrderItems(): Flow<List<OrderItemAndProduct>>

    @Upsert
    fun upsertOrder(orderItem: OrderItemEntity)

    @Query("DELETE FROM order_item WHERE orderItemId = :code")
    fun deleteOrderItem(code: String)

    @Query("UPDATE product SET orderOwnerItemId = :orderItemId " +
            "WHERE productId = :productCode")
    fun updateOrderParentInProduct(productCode: String, orderItemId: String?)

    @Query("UPDATE product SET quantity = :quantity " +
            "WHERE productId = :productCode")
    fun updateQuantityInProduct(productCode: String, quantity: Int)

    @Transaction
    fun upsertOrderAndUpdateProductTransaction(orderItem: OrderItemEntity) {
        val orderId = orderItem.orderItemId
        upsertOrder(orderItem)
        updateOrderParentInProduct(orderId, orderId)
        updateQuantityInProduct(orderId, orderItem.quantity)
    }

    @Transaction
    fun deleteOrderAndUpdateProductTransaction(code: String) {
        deleteOrderItem(code)
        updateOrderParentInProduct(code, null)
        updateQuantityInProduct(code, 0)
    }
}
