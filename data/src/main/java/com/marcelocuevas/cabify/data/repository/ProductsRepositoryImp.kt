package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.mapper.Mapper
import com.marcelocuevas.cabify.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(
    private val dataSource: ProductsDataSource,
    private val mapper: Mapper
): ProductsRepository {

    override fun getProducts(): Flow<List<Product>> {
        return dataSource.getProducts().map { mapper.map(it) }
    }
}