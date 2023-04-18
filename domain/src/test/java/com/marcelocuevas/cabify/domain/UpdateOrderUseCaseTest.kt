package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateOrderUseCaseTest {

    companion object {
        private const val TEST_PRODUCT_CODE = "VOUCHER"
    }

    private var repository: OrdersRepository = mock()
    private lateinit var updateOrder: UpdateOrderUseCase

    @Before
    fun setup() {
        updateOrder = UpdateOrderUseCase(repository)
    }

    @Test
    fun  `useCase invoked with an INCREASE_ACTION and quantity zero should increase quantity and call repository`() = runTest {
        val currentQuantity = 0
        updateOrder.invoke(
            productId = TEST_PRODUCT_CODE,
            quantity = currentQuantity,
            action = OrderAction.INCREASE_ORDER_QUANTITY
        )

        verify(repository).upsertOrderItem(TEST_PRODUCT_CODE, currentQuantity + 1)
    }

    @Test
    fun  `useCase invoked with an DECREASE_ACTION and quantity zero should not call repository`() = runTest {
        val currentQuantity = 0
        updateOrder.invoke(
            productId = TEST_PRODUCT_CODE,
            quantity = currentQuantity,
            action = OrderAction.DECREASE_ORDER_QUANTITY
        )

        verify(repository, never()).upsertOrderItem(TEST_PRODUCT_CODE, currentQuantity - 1)
    }

    @Test
    fun  `useCase invoked with an DECREASE_ACTION and quantity greater than 0 should call repository update order`() = runTest {
        val currentQuantity = 3
        updateOrder.invoke(
            productId = TEST_PRODUCT_CODE,
            quantity = currentQuantity,
            action = OrderAction.DECREASE_ORDER_QUANTITY
        )

        verify(repository).upsertOrderItem(TEST_PRODUCT_CODE, currentQuantity - 1)
    }

    @Test
    fun  `useCase invoked with an DECREASE_ACTION and quantity 1 should call repository delete order`() = runTest {
        val currentQuantity = 1
        updateOrder.invoke(
            productId = TEST_PRODUCT_CODE,
            quantity = currentQuantity,
            action = OrderAction.DECREASE_ORDER_QUANTITY
        )

        verify(repository).upsertOrderItem(TEST_PRODUCT_CODE, currentQuantity - 1)
        verify(repository).deleteOrderItem(TEST_PRODUCT_CODE)
    }

    @Test
    fun  `useCase invoked with an DECREASE_ACTION and quantity negative should not call repository`() = runTest {
        val currentQuantity = -1
        updateOrder.invoke(
            productId = TEST_PRODUCT_CODE,
            quantity = currentQuantity,
            action = OrderAction.DECREASE_ORDER_QUANTITY
        )

        verify(repository, never()).upsertOrderItem(TEST_PRODUCT_CODE, currentQuantity - 1)
    }
}