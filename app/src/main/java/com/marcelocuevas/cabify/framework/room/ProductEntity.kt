package com.marcelocuevas.cabify.framework.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey
    val code: String = "",
    val name: String,
    val price: String,
    val priceWithCurrency: String,
    val currency: String? = "€",
    val promotionDescription: String,
    val imageUrl: String
)
