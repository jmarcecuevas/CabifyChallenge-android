package com.marcelocuevas.cabify.ui.home

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.model.Product
import javax.annotation.concurrent.Immutable

@Immutable
sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(

        val shouldShowOrdersBottomSheet: Boolean = false,

        val products: List<Product>,
        val orders: List<OrderItemAndProduct>
    ) : HomeUiState {

        init {
            products.forEach { product ->
                orders.forEach { order ->
                    if (product.code == order.product!!.code) {
                        product.quantity = order.orderItem.quantity
                    }
                }
            }
        }
    }
}