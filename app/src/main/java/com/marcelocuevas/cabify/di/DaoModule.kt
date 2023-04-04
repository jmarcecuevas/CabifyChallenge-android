package com.marcelocuevas.cabify.di

import com.marcelocuevas.cabify.framework.room.CabifyDatabase
import com.marcelocuevas.cabify.framework.room.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun providesAuthorDao(
        database: CabifyDatabase,
    ): ProductDao = database.productsDao()
}