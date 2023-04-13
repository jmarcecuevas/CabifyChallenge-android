package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.GetProductsUseCase
import com.marcelocuevas.cabify.domain.DeleteFromCartUseCase
import com.marcelocuevas.cabify.domain.GetCartUseCase
import com.marcelocuevas.cabify.domain.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getCart: GetCartUseCase,
    private val updateCart: UpdateCartUseCase,
    private val deleteFromCart: DeleteFromCartUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

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

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            updateCart.invoke(code, quantity = currentQuantity + 1)
        }

    fun onDecreaseItemCount(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            if (currentQuantity > 0) {
                updateCart.invoke(code, quantity = currentQuantity - 1)
                if (currentQuantity == 1) {
                    deleteFromCart.invoke(code)
                }
            }
        }
    }
}
