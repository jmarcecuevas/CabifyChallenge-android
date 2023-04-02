package com.marcelocuevas.cabify.usecases

import com.marcelocuevas.cabify.domain.repository.ProductsRepository

class GetProductsUseCase(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(query: String) {

    }

}