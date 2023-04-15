package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import javax.inject.Inject
import com.marcelocuevas.cabify.domain.OrderAction.INCREASE_QUANTITY
import com.marcelocuevas.cabify.domain.OrderAction.DECREASE_QUANTITY

class UpdateCartUseCase @Inject constructor(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(
        productId: String,
        quantity: Int,
        orderAction: OrderAction
    ) {
        when (orderAction) {
            INCREASE_QUANTITY -> increaseQuantity(productId,quantity)
            DECREASE_QUANTITY -> decreaseQuantity(productId, quantity)
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
    INCREASE_QUANTITY,
    DECREASE_QUANTITY
}
