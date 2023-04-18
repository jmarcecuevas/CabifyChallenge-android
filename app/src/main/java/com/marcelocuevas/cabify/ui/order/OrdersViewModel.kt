package com.marcelocuevas.cabify.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.DeleteOrderUseCase
import com.marcelocuevas.cabify.domain.GetOrdersUseCase
import com.marcelocuevas.cabify.domain.UpdateOrderUseCase
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
    private val updateOrder: UpdateOrderUseCase,
    private val deleteFromCart: DeleteOrderUseCase
): ViewModel() {

    val uiState: StateFlow<OrdersUiState> =
        getOrders.invoke()
            .map { orders ->
                if (orders.isNotEmpty()) {
                    OrdersUiState.HasOrders(
                        itemsAddedQuantity = orders.sumOf { it.orderItem.quantity },
                        total = orders.sumOf { it.total },
                        subtotal = orders.sumOf { it.subtotal },
                        orders = orders
                    )
                } else {
                    OrdersUiState.NoOrders
                }}
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribed(5000),
                initialValue = OrdersUiState.Loading
            )

    fun removeOrder(code: String) {
        viewModelScope.launch {
            deleteFromCart.invoke(code)
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
                action = DECREASE_ORDER_QUANTITY)
        }
    }
}
