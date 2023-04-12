package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.CartRepository
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(productId: String, quantity: Int) {
        repository.upsertOrderItem(productId, quantity)
    }
}
