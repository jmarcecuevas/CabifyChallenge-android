package com.marcelocuevas.cabify.data

import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.datasource.LocalProductsDataSource
import com.marcelocuevas.cabify.data.datasource.ProductsDataSource
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import com.marcelocuevas.cabify.data.repository.impl.OfflineFirstProductsRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OfflineFirstRepositoryTest {

    private var localDataSource: LocalProductsDataSource = mock()
    private var remoteDataSource: ProductsDataSource = mock()
    private var mapper: (ProductItemDTO) -> (Product) = mock()

    private var repository: ProductsRepository =
        OfflineFirstProductsRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            mapper = mapper
        )

    @Test
    fun `when no products in local db, getProducts should refresh from remote`() = runTest {
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.getProducts()).thenReturn(emptyList())

        repository.getProducts()

        verify(remoteDataSource).getProducts()
    }

    @Test
    fun `when there are products in local db, getProducts should return local products`() = runTest {
        whenever(localDataSource.isEmpty()).thenReturn(false)

        repository.getProducts()

        verify(localDataSource).getProductsStream()
    }

    @Test
    fun `refreshProducts should fetch products from remote and update db`() = runTest {
        whenever(remoteDataSource.getProducts()).thenReturn(emptyList())

        repository.refreshProducts()

        verify(remoteDataSource).getProducts()
        verify(localDataSource).insertProductsIfNotExist(emptyList())
    }
}
