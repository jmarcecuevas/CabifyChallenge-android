package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "order_item"/*,
    indices = [Index(value = ["productId"], unique = true)]*/
)
data class OrderItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "orderItemId")
    val productId: String,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "subtotal")
    var subtotal: Double? = 0.0,
)
