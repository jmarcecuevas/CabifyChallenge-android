package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.CartRepository
import javax.inject.Inject

class DeleteFromCartUseCase @Inject constructor(
    private val repository: CartRepository
) {

    suspend operator fun invoke(code: String) {
        repository.deleteOrderItem(code)
    }
}
