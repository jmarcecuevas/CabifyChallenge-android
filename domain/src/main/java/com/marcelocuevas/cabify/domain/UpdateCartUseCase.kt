package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(productId: String, quantity: Int) {
        repository.upsertOrderItem(productId, quantity)
    }
}
