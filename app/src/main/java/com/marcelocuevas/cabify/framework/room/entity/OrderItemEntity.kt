package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_item")
data class OrderItemEntity(
    @PrimaryKey
    val orderItemId: String,
    var quantity: Int = 0,
)
