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
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrders: GetCartUseCase,
    private val updateCart: UpdateCartUseCase,
    private val deleteFromCart: DeleteFromCartUseCase
): ViewModel() {

    val uiState: StateFlow<OrdersUiState> =
        getOrders.invoke()
            .map { OrdersUiState(
                orders = it,
                qtyItemsAdded = it.sumOf { it.orderItem.quantity },
                total = it.sumOf { it.total },
                subtotal = it.sumOf { it.subtotal }
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
            updateCart.invoke(code, currentQuantity, INCREASE_QUANTITY)
        }

    fun onDecreaseItemClicked(code: String, currentQuantity: Int) {
        viewModelScope.launch {
            updateCart.invoke(code, currentQuantity, DECREASE_QUANTITY)
        }
    }
}

@Immutable
data class OrdersUiState(
    val orders: List<OrderItemAndProduct>,
    val qtyItemsAdded: Int = 0,
    val total: Double = 0.0,
    val subtotal: Double = 0.0,
)

fun OrdersUiState.shouldShowOldPrice() = total != subtotal
