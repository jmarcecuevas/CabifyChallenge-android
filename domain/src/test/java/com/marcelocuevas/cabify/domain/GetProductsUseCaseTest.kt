package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GetProductsUseCaseTest {

    @Mock
    private lateinit var repository: ProductsRepository
    private lateinit var getProducts: GetProductsUseCase

    @Before
    fun setup() {
        getProducts = GetProductsUseCase(repository)
    }

    @Test
    fun  `when useCase is invoked should call repository getProducts method`() = runTest {
        getProducts.invoke()

        verify(repository).getProducts()
    }
}