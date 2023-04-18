package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
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
class GetOrdersUseCaseTest {

    @Mock
    private lateinit var repository: OrdersRepository
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