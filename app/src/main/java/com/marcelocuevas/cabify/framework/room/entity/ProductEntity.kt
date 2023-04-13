package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "productId")
    val code: String,

    @ColumnInfo(name = "orderOwnerItemId")
    val orderOwnerItemId: String?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "price")
    var price: Double,

    @ColumnInfo(name = "promotionDescription")
    var promotionDescription: String,

    @ColumnInfo("imageUrl")
    val imageUrl: String
)