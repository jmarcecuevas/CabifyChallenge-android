package com.marcelocuevas.cabify.framework.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(

    @PrimaryKey
    val code: String,
    val name: String,
    val price: Double,
    val currency: String = "€",
    val imageUrl: String,
    val quantity: Int,
    val totalItemPrice: Double = price * quantity
)
