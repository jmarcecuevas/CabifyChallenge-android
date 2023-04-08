package com.marcelocuevas.cabify.framework.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val code: String,
    val name: String,
    val price: String,
    val currency: String = "€",
    val imageUrl: String,
    val quantity: Int,
    val totalItemPrice: Double
)