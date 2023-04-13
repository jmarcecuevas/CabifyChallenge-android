package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val repository: CartRepository
) {

    operator fun invoke(): Flow<List<OrderItemAndProduct>> {
        //return flow { emit(emptyList<OrderItemAndProduct>()) }
        return repository.getOrderItems()
    }
}
