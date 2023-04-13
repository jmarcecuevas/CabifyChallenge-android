package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.*
import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Transaction
    @Query("SELECT * FROM order_item")
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

//    @Query("SELECT *, (SELECT sum(spent_table.amount)  FROM spent_table WHERE spent_table.accountId = a.id) AS sumOfSpent   FROM account_table AS a")

}
