package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
//    //Get all line item in along with its product
//    @Transaction
//    @Query("SELECT * FROM line_items ")
//    fun getAlOrders(): Flow<List<OrderItemAndProduct>>

    @Upsert
    fun upsertOrder(orderItem: OrderItemEntity): Long
}
