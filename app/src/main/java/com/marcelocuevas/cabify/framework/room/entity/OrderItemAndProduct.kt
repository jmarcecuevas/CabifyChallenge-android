package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.Embedded
import androidx.room.Relation

data class OrderItemAndProduct(
    @Embedded val orderItem: OrderItemEntity,
    @Relation(
        parentColumn = "orderItemId",
        entityColumn = "orderOwnerItemId",
    )
    val product: ProductEntity,
    val subtotal: Double
)
