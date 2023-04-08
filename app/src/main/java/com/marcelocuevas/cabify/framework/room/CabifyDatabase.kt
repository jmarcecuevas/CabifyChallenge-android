package com.marcelocuevas.cabify.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
private const val DATABASE_VERSION = 1

@Database(
    entities = [ProductEntity::class, CartItemEntity::class],
    version = DATABASE_VERSION
)
abstract class CabifyDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    abstract fun cartDao(): CartDao
}
