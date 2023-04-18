package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RefreshProductsUseCaseTest {

    private var repository: ProductsRepository = mock()
    private lateinit var refreshProducts: RefreshProductsUseCase

    @Before
    fun setup() {
        refreshProducts = RefreshProductsUseCase(repository)
    }

    @Test
    fun  `when useCase is invoked should call repository refreshProducts method`() = runTest {
        refreshProducts.invoke()

        verify(repository).refreshProducts()
    }
}