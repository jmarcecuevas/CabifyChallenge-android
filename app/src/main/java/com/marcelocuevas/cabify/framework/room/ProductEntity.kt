package com.marcelocuevas.cabify.framework.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.marcelocuevas.cabify.data.model.Product

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

fun ProductEntity.asExternalModel() = Product(
    code = code,
    name = name,
    price = price,
    priceWithCurrency = priceWithCurrency,
    currency = currency,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)
