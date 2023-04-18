package com.marcelocuevas.cabify.data

import com.marcelocuevas.cabify.data.datasource.OrderDataSource
import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.discount.DiscountCalculator
import com.marcelocuevas.cabify.data.repository.OrdersRepository
import com.marcelocuevas.cabify.data.repository.impl.OrdersRepositoryImp
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OrderRepositoryTest {

    private var orderDataSource: OrderDataSource = mock()
    private lateinit var discountCalculator: DiscountCalculator

    private lateinit var repository: OrdersRepository

    @Before
    fun setup() {
        discountCalculator = DiscountCalculator()
        repository = OrdersRepositoryImp(
            orderDataSource = orderDataSource,
            discountCalculator = discountCalculator
        )
    }

    @Test
    fun `upsertOrderItem should call dataSource with a created OrderItem instance with provided parameters`() = runTest {
        repository.upsertOrderItem(
            orderId = ORDER_ID,
            quantity = QUANTITY
        )

        verify(orderDataSource).upsertOrderItem(OrderItem(ORDER_ID, QUANTITY))
    }

    @Test
    fun `getOrderItems should call ordersDataSource to get the orders`() = runTest {
        repository.getOrderItems()

        verify(orderDataSource).getOrderItemsStream()
    }

    @Test
    fun `getOrderItems should apply discount to every order collected`() = runTest {
        whenever(orderDataSource.getOrderItemsStream()).thenReturn(flow {
            emit ( emptyList() )
        })

        val orders = repository.getOrderItems()

        orders.map {
            it.map { order ->
                verify(discountCalculator).applyDiscount(
                    code = order.product.code,
                    quantity = order.product.quantity,
                    unitPrice = order.product.price
                )
            }
        }
    }

    @Test
    fun `deleteOrderItem should call orderDataSource delete method`() = runTest {
        repository.deleteOrderItem(ORDER_ID)

        verify(orderDataSource).deleteOrderItem(ORDER_ID)
    }

    companion object {
        private const val ORDER_ID = "VOUCHER"
        private const val QUANTITY = 2
    }
}
