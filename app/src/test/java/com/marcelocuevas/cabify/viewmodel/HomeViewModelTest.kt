package com.marcelocuevas.cabify.viewmodel

import com.marcelocuevas.cabify.*
import com.marcelocuevas.cabify.domain.*
import com.marcelocuevas.cabify.fakerepository.TestOrdersRepository
import com.marcelocuevas.cabify.fakerepository.TestProductsRepository
import com.marcelocuevas.cabify.testOrdersList
import com.marcelocuevas.cabify.testProductList
import com.marcelocuevas.cabify.ui.home.HomeUiState
import com.marcelocuevas.cabify.ui.home.HomeViewModel
import com.marcelocuevas.cabify.util.MainDispatcherRule
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
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val productsRepository = TestProductsRepository()
    private val ordersRepository = TestOrdersRepository()

    private val getProductsUseCase = GetProductsUseCase(
        repository = productsRepository
    )
    private val refreshProductsUseCase = RefreshProductsUseCase(
        repository = productsRepository
    )
    private val getOrdersUseCase = GetOrdersUseCase(
        repository = ordersRepository
    )
    private val updateOrdersUseCase = UpdateOrderUseCase(
        repository = ordersRepository
    )

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getProducts = getProductsUseCase,
            refreshProducts = refreshProductsUseCase,
            getOrders = getOrdersUseCase,
            updateOrder = updateOrdersUseCase
        )
    }

    @Test
    fun `uiState is initially Loading`() = runTest {
        assertEquals(HomeUiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `isRefreshing is initially false`() = runTest {
        assertEquals(false, viewModel.isRefreshing.value)
    }

    @Test
    fun `uiState when products are empty and orders are collected`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, HomeUiState.Loading)

        productsRepository.sendProductsItems(emptyList())
        ordersRepository.sendOrderItems(testOrdersList)

        assertEquals(viewModel.uiState.value, HomeUiState.Success(
            showOrderView = true,
            itemsAddedQuantity = 1,
            subtotal = 5.0,
            products = emptyList()
        ))

        collectJob.cancel()
    }

    @Test
    fun `uiState when products are not empty and orders are empty`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, HomeUiState.Loading)

        productsRepository.sendProductsItems(testProductList)
        ordersRepository.sendOrderItems(emptyList())

        assertEquals(viewModel.uiState.value, HomeUiState.Success(
            showOrderView = false,
            itemsAddedQuantity = 0,
            subtotal = 0.0,
            products = testProductList
        ))

        collectJob.cancel()
    }

    @Test
    fun `uiState when products and orders are not empty`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, HomeUiState.Loading)

        productsRepository.sendProductsItems(testProductList)
        ordersRepository.sendOrderItems(testOrdersList)

        assertEquals(viewModel.uiState.value, HomeUiState.Success(
            showOrderView = true,
            itemsAddedQuantity = 1,
            subtotal = 5.0,
            products = testProductList
        ))

        collectJob.cancel()
    }

    @Test
    fun `uiState when products and orders are empty`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.uiState.collect()
        }
        assertEquals(viewModel.uiState.value, HomeUiState.Loading)

        productsRepository.sendProductsItems(emptyList())
        ordersRepository.sendOrderItems(emptyList())

        assertEquals(viewModel.uiState.value, HomeUiState.Success(
            showOrderView = false,
            itemsAddedQuantity = 0,
            subtotal = 0.0,
            products = emptyList()
        ))

        collectJob.cancel()
    }

    @Test
    fun `isRefreshing is false after call refreshProducts method`() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.isRefreshing.collect()
        }

        viewModel.refreshProducts()
        assertEquals(viewModel.isRefreshing.value, false)

        collectJob.cancel()
    }
}
