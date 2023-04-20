package com.marcelocuevas.cabify.viewmodel

import com.marcelocuevas.cabify.util.MainDispatcherRule
import com.marcelocuevas.cabify.fakerepository.TestOrdersRepository
import com.marcelocuevas.cabify.domain.DeleteOrderUseCase
import com.marcelocuevas.cabify.domain.EmptyTheCartUseCase
import com.marcelocuevas.cabify.domain.GetOrdersUseCase
import com.marcelocuevas.cabify.domain.UpdateOrderUseCase
import com.marcelocuevas.cabify.testOrdersList
import com.marcelocuevas.cabify.ui.order.OrdersUiState
import com.marcelocuevas.cabify.ui.order.OrdersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OrdersViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val ordersRepository = TestOrdersRepository()
    private val getOrdersUseCase = GetOrdersUseCase(
            repository = ordersRepository
    )
    private val updateOrderUseCase = UpdateOrderUseCase(
        repository = ordersRepository
    )
    private val deleteFromCartUseCase = DeleteOrderUseCase(
        repository = ordersRepository
    )
    private val emptyTheCartUseCase = EmptyTheCartUseCase(
        repository = ordersRepository
    )

    private lateinit var viewModel: OrdersViewModel

    @Before
    fun setup() {
        viewModel = OrdersViewModel(
            getOrders = getOrdersUseCase,
            updateOrder = updateOrderUseCase,
            deleteFromCart = deleteFromCartUseCase,
            emptyTheCart = emptyTheCartUseCase
        )
    }

    @Test
    fun `uiState is initially Loading`() = runTest {
        assertEquals(OrdersUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `uiState is NoOrders state when orders list is empty`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, OrdersUiState.Loading)

        ordersRepository.sendOrderItems(emptyList())

        assertEquals(viewModel.uiState.value, OrdersUiState.NoOrders)

        collectJob.cancel()
    }

    @Test
    fun `uiState is HasOrders state when orders list is not empty`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, OrdersUiState.Loading)

        ordersRepository.sendOrderItems(testOrdersList)

        assertEquals(viewModel.uiState.value, OrdersUiState.HasOrders(
            itemsAddedQuantity = 1,
            total = 5.0,
            subtotal = 5.0,
            orders = testOrdersList
        ))

        collectJob.cancel()
    }
}