package com.marcelocuevas.cabify.di

import com.marcelocuevas.cabify.data.datasource.NetworkProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.marcelocuevas.cabify.data.repository.OfflineFirstProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindProductsRepository(repositoryImp: OfflineFirstProductsRepository): ProductsRepository

    @Binds
    abstract fun bindProductsDataSource(dataSourceImp: NetworkProductsDataSource): ProductsDataSource
}