package com.marcelocuevas.cabify.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.domain.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrders: GetCartUseCase
): ViewModel() {

    val orders: StateFlow<List<OrderItemAndProduct>> =
        getOrders.invoke()
            .map { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun removeOrder(code: Long) {

    }

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            //updateCart.invoke(code, quantity = currentQuantity + 1)
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            if (currentQuantity > 0) {
                //updateCart.invoke(code, quantity = currentQuantity - 1)
                if (currentQuantity == 1) {
                    //deleteFromCart.invoke(code)
                }
            }
        }
    }
}
