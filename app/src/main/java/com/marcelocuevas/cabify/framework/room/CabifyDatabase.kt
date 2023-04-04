package com.marcelocuevas.cabify.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CabifyDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "cabify.db"
        private var instance: CabifyDatabase? = null

        private fun create(context: Context): CabifyDatabase =
            Room.databaseBuilder(context, CabifyDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): CabifyDatabase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun productsDao(): ProductDao
}
