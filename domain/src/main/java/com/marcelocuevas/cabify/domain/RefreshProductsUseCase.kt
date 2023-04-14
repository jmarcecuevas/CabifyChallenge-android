package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.ProductsRepository
import javax.inject.Inject

class RefreshProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
){

    suspend operator fun invoke() =
        repository.refreshProducts()
}