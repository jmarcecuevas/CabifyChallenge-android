package com.marcelocuevas.cabify.di

import android.content.Context
import androidx.room.Room
import com.marcelocuevas.cabify.framework.room.CabifyDatabase
import com.marcelocuevas.cabify.framework.room.CartDao
import com.marcelocuevas.cabify.framework.room.ProductDao
import com.marcelocuevas.cabify.framework.room.entity.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val APP_DATABASE_NAME = "cabify.db"

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCabifyDatabase(
        @ApplicationContext context: Context
    ): CabifyDatabase {
        return Room.databaseBuilder(
            context,
            CabifyDatabase::class.java,
            APP_DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideProductDao(database: CabifyDatabase): ProductDao {
        return database.productDao()
    }

    @Singleton
    @Provides
    fun provideOrderDao(database: CabifyDatabase): OrderDao {
        return database.orderDao()
    }
}
