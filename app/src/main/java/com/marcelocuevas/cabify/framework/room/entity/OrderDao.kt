package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.*
import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Transaction
    @Query("SELECT *, (i.quantity * p.price) as subtotal FROM order_item i INNER JOIN product p ON p.productId == i.orderItemId")
    fun getAllOrderItems(): Flow<List<OrderItemAndProduct>>


    @Upsert
    fun upsertOrder(orderItem: OrderItemEntity)

    @Query("UPDATE product SET orderOwnerItemId = :orderItemId WHERE productId = :productCode")
    fun updateOrderIdInProduct(productCode: String, orderItemId: String?)

    @Transaction
    fun todo(orderItem: OrderItemEntity) {
        upsertOrder(orderItem)
        updateOrderIdInProduct(orderItem.productId, orderItem.productId)
    }

    @Query("DELETE FROM order_item WHERE orderItemId = :code")
    fun deleteOrderItem(code: String)

    @Transaction
    fun algo(code: String) {
        deleteOrderItem(code)
        updateOrderIdInProduct(code, null)
    }

}
