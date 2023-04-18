package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.GetProductsUseCase
import com.marcelocuevas.cabify.domain.RefreshProductsUseCase
import com.marcelocuevas.cabify.domain.GetOrdersUseCase
import com.marcelocuevas.cabify.domain.UpdateOrderUseCase
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_ORDER_QUANTITY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val refreshProducts: RefreshProductsUseCase,
    private val getOrders: GetOrdersUseCase,
    private val updateOrder: UpdateOrderUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    init {
        collectHomeData()
    }

    private fun collectHomeData() {
        viewModelScope.launch {
            combine(
                getProducts.invoke(),
                getOrders.invoke()
            ) { products, orders ->
                HomeUiState.Success(
                    showOrderView = orders.isNotEmpty(),
                    itemsAddedQuantity = orders.sumOf { it.orderItem.quantity },
                    subtotal = orders.sumOf { it.subtotal },
                    products = products
                )
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun refreshProducts() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshProducts.invoke()
            _isRefreshing.value = false
        }
    }

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            updateOrder.invoke(
                productId = code,
                quantity = currentQuantity,
                action = INCREASE_ORDER_QUANTITY
            )
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateOrder.invoke(
                productId = code,
                quantity = currentQuantity,
                action = DECREASE_ORDER_QUANTITY
            )
        }
    }
}
