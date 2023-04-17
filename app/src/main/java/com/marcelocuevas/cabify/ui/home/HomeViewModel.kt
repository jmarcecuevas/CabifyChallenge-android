package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getCart: GetCartUseCase,
    private val updateCart: UpdateCartUseCase,
    private val refreshProducts: RefreshProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            combine(
                getProducts.invoke(),
                getCart.invoke()
            ) { products, orders ->
                HomeUiState.Success(
                    shouldShowOrdersBottomSheet = orders.isNotEmpty(),
                    products = products,
                    orders = orders
                )
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshProducts.invoke()
            _isRefreshing.value = false
        }
    }

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            updateCart.invoke(code, currentQuantity, OrderAction.INCREASE_ORDER_QUANTITY)
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateCart.invoke(code, currentQuantity, OrderAction.DECREASE_ORDER_QUANTITY)
        }
    }
}
