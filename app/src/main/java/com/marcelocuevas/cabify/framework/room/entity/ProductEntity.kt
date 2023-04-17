package com.marcelocuevas.cabify.framework.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "productId")
    val code: String,
    val orderOwnerItemId: String?,
    val name: String,
    val price: Double,
    val quantity: Int = 0
)
