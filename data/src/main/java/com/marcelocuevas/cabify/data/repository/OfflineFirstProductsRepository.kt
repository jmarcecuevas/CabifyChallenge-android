package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.toDataModel
import com.marcelocuevas.cabify.data.network.Result
import com.marcelocuevas.cabify.data.network.toListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val remoteDataSource: ProductsDataSource,
    private val localDataSource: LocalProductsDataSource,
    private val mapper: (ProductItemDTO) -> (Product)
): ProductsRepository {

    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        return flow {
            emit ( remoteDataSource
                .getProducts()
                .toListResult()
                .toDataModel(mapper)
            )
        }
    }

    override fun getProductsStream(): Flow<List<Product>> {
        return localDataSource.getProductsStream()
    }

    override suspend fun saveProducts(products: List<Product>) {
        localDataSource.saveProducts(products)
    }
}
