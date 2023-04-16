package com.marcelocuevas.cabify.di

import com.marcelocuevas.cabify.data.api.ProductsAPI
import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.NetworkProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.mapper.makeProductDtoMapper
import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.marcelocuevas.cabify.data.repository.OrdersRepositoryImp
import com.marcelocuevas.cabify.data.repository.OfflineFirstProductsRepository
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.marcelocuevas.cabify.framework.room.ProductDao
import com.marcelocuevas.cabify.framework.room.RoomOrderDataSource
import com.marcelocuevas.cabify.framework.room.RoomProductsDataSource
import com.marcelocuevas.cabify.framework.room.entity.OrderProductsDao
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
    fun provideLocalProductsDataSource(productsDao: ProductDao): LocalProductsDataSource =
        RoomProductsDataSource(productsDao)

    @Provides
    fun provideOrderDataSource(orderDao: OrderProductsDao): OrderDataSource =
        RoomOrderDataSource(orderDao)

    @Provides
    fun provideProductsRepository(
        remoteDataSource: ProductsDataSource,
        localDataSource: LocalProductsDataSource,
    ): ProductsRepository =
        OfflineFirstProductsRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            mapper = makeProductDtoMapper()
        )

    @Provides
    fun provideCartRepository(
        orderDataSource: OrderDataSource,
        discountCalculator: DiscountCalculator
    ): OrdersRepository =
        OrdersRepositoryImp(orderDataSource, discountCalculator)
}
