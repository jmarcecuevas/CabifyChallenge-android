package com.marcelocuevas.cabify.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_QUANTITY
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.domain.DeleteFromCartUseCase
import com.marcelocuevas.cabify.domain.GetCartUseCase
import com.marcelocuevas.cabify.domain.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrders: GetCartUseCase,
    private val updateCart: UpdateCartUseCase,
    private val deleteFromCart: DeleteFromCartUseCase
): ViewModel() {

    val orders: StateFlow<List<OrderItemAndProduct>> =
        getOrders.invoke()
            .map { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun removeOrder(code: String) {
        viewModelScope.launch {
            deleteFromCart.invoke(code)
        }
    }

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            updateCart.invoke(code, currentQuantity, INCREASE_QUANTITY)
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateCart.invoke(code, currentQuantity, DECREASE_QUANTITY)
        }
    }
}
