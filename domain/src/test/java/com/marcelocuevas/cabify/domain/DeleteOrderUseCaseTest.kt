package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeleteOrderUseCaseTest {

    companion object {
        private const val PRODUCT_CODE = "VOUCHER"
        private const val EMPTY_PRODUCT_CODE = ""
    }

    private var repository: OrdersRepository = mock()
    private lateinit var deleteOrder: DeleteOrderUseCase

    @Before
    fun setup() {
        deleteOrder = DeleteOrderUseCase(repository)
    }

    @Test
    fun  `when useCase is invoked with a not empty product code should call repository`() = runTest {
        deleteOrder.invoke(PRODUCT_CODE)

        verify(repository).deleteOrderItem(PRODUCT_CODE)
    }

    @Test
    fun  `when useCase is invoked with an empty product code should call repository`() = runTest {
        deleteOrder.invoke(EMPTY_PRODUCT_CODE)

        verify(repository).deleteOrderItem(EMPTY_PRODUCT_CODE)
    }
}
