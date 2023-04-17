package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.*
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
    private val updateCart: UpdateCartUseCase,
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
            updateCart.invoke(
                productId = code,
                quantity = currentQuantity,
                action = OrderAction.INCREASE_ORDER_QUANTITY
            )
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateCart.invoke(
                productId = code,
                quantity = currentQuantity,
                action = OrderAction.DECREASE_ORDER_QUANTITY
            )
        }
    }
}
