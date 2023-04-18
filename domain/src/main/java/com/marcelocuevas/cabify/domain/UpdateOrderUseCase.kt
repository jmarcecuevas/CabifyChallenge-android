package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import javax.inject.Inject
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_ORDER_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_ORDER_QUANTITY

class UpdateOrderUseCase @Inject constructor(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(
        productId: String,
        quantity: Int,
        action: OrderAction
    ) {
        when (action) {
            INCREASE_ORDER_QUANTITY -> increaseQuantity(productId,quantity)
            DECREASE_ORDER_QUANTITY -> decreaseQuantity(productId, quantity)
        }
    }

    private suspend fun increaseQuantity(id: String, quantity: Int) {
        repository.upsertOrderItem(id, quantity + 1)
    }

    private suspend fun decreaseQuantity(id: String, quantity: Int) {
        if (quantity > 0) {
            repository.upsertOrderItem(id,quantity - 1)
            if (quantity == 1) {
                repository.deleteOrderItem(id)
            }
        }
    }
}

enum class OrderAction{
    INCREASE_ORDER_QUANTITY,
    DECREASE_ORDER_QUANTITY
}
