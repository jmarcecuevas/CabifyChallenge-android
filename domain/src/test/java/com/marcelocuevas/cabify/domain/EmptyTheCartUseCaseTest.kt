package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class EmptyTheCartUseCaseTest {

    private var repository: OrdersRepository = mock()
    private lateinit var emptyTheCart: EmptyTheCartUseCase

    @Before
    fun setup() {
        emptyTheCart = EmptyTheCartUseCase(repository)
    }

    @Test
    fun  `when useCase is invoked should call repository refreshProducts method`() = runTest {
        emptyTheCart.invoke()

        verify(repository).deleteAllOrderItems()
    }
}
