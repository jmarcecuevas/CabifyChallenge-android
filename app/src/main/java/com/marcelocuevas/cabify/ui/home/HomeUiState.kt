package com.marcelocuevas.cabify.ui.home

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.Product
import javax.annotation.concurrent.Immutable

@Immutable
sealed interface HomeUiState {
    object Loading : HomeUiState

    class Success(
        val shouldShowOrdersBottomSheet: Boolean = false,
        var qtyItemsAdded: Int = 0,
        var subtotal: Double = 0.0,

        val products: List<Product>,
        val orders: List<OrderItemAndProduct>
    ) : HomeUiState {

        init {
            qtyItemsAdded = orders.sumOf { it.orderItem.quantity }
            subtotal = orders.sumOf { it.subtotal }
        }
    }
}
