package com.marcelocuevas.cabify.di

import com.marcelocuevas.cabify.data.api.ProductsAPI
import com.marcelocuevas.cabify.data.datasource.CartDataSource
import com.marcelocuevas.cabify.data.datasource.NetworkProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.mapper.makeProductDtoMapper
import com.marcelocuevas.cabify.data.repository.OfflineFirstProductsRepository
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.marcelocuevas.cabify.framework.room.CartDao
import com.marcelocuevas.cabify.framework.room.RoomCartDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideProductsDataSource(api: ProductsAPI): ProductsDataSource =
        NetworkProductsDataSource(api)

    @Provides
    fun provideCartDataSource(dao: CartDao): CartDataSource =
        RoomCartDataSource(dao)

    @Provides
    fun provideProductsRepository(
        productsDataSource: ProductsDataSource,
        cartDataSource: CartDataSource,
    ): ProductsRepository =
        OfflineFirstProductsRepository(
            productsDataSource = productsDataSource,
            cartDataSource = cartDataSource,
            mapper = makeProductDtoMapper()
        )
}
