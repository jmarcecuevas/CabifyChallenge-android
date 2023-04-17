package com.marcelocuevas.cabify.ui.order

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import javax.annotation.concurrent.Immutable

@Immutable
data class OrdersUiState(
    val itemsAddedQuantity: Int = 0,
    val total: Double = 0.0,
    val subtotal: Double = 0.0,
    val orders: List<OrderItemAndProduct>,
)
