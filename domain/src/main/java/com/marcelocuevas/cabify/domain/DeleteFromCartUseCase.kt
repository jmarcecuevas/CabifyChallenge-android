package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.OrdersRepository
import javax.inject.Inject

class DeleteFromCartUseCase @Inject constructor(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(code: String) {
        repository.deleteOrderItem(code)
    }
}
