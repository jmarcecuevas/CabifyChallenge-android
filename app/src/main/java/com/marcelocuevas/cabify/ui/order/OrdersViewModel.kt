package com.marcelocuevas.cabify.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.DeleteOrderUseCase
import com.marcelocuevas.cabify.domain.GetOrdersUseCase
import com.marcelocuevas.cabify.domain.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    getOrders: GetOrdersUseCase,
    private val updateCart: UpdateCartUseCase,
    private val deleteFromCart: DeleteOrderUseCase
): ViewModel() {

    val uiState: StateFlow<OrdersUiState> =
        getOrders.invoke()
            .map { orders ->
                OrdersUiState(
                    itemsAddedQuantity = orders.sumOf { it.orderItem.quantity },
                    total = orders.sumOf { it.total },
                    subtotal = orders.sumOf { it.subtotal },
                    orders = orders
            ) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5000),
                initialValue = OrdersUiState(orders = emptyList())
            )

    fun removeOrder(code: String) {
        viewModelScope.launch {
            deleteFromCart.invoke(code)
        }
    }

    fun onIncreaseItemClicked(code: String, currentQuantity: Int) =
        viewModelScope.launch {
            updateCart.invoke(
                productId = code,
                quantity = currentQuantity,
                action = INCREASE_ORDER_QUANTITY
            )
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateCart.invoke(
                productId = code,
                quantity = currentQuantity,
                action = DECREASE_ORDER_QUANTITY)
        }
    }
}
