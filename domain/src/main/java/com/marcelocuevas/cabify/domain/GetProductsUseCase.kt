package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}
