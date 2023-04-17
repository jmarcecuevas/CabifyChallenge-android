package com.marcelocuevas.cabify.framework.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marcelocuevas.cabify.framework.room.dao.OrderProductsDao
import com.marcelocuevas.cabify.framework.room.dao.ProductDao
import com.marcelocuevas.cabify.framework.room.entity.OrderItemEntity
import com.marcelocuevas.cabify.framework.room.entity.ProductEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [ProductEntity::class, OrderItemEntity::class],
    version = DATABASE_VERSION
)
abstract class CabifyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun orderProductsDao(): OrderProductsDao
}
