package com.marcelocuevas.cabify.data.repository

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.datasource.CartDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.model.CartItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.toDataModel
import com.marcelocuevas.cabify.data.network.Result
import com.marcelocuevas.cabify.data.network.toListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OfflineFirstProductsRepository @Inject constructor(
    private val productsDataSource: ProductsDataSource,
    private val cartDataSource: CartDataSource,
    private val mapper: (ProductItemDTO) -> (Product)
): ProductsRepository {

    override suspend fun getProducts(): Flow<Result<List<Product>>> {
        return flow {
            emit ( productsDataSource
                .getProducts()
                .toListResult()
                .toDataModel(mapper)
            )
        }
    }

    override suspend fun getCartItems(): Flow<List<CartItem>> =
        cartDataSource.getCartItems()
}
