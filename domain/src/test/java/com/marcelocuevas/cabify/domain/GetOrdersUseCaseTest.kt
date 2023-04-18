package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetOrdersUseCaseTest {

    private var repository: OrdersRepository = mock()
    private lateinit var getOrders: GetOrdersUseCase

    @Before
    fun setup() {
        getOrders = GetOrdersUseCase(repository)
    }

    @Test
    fun  `when useCase is invoked should call repository getOrderItems method`() = runTest {
        getOrders.invoke()

        verify(repository).getOrderItems()
    }
}