package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.data.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetOrdersUseCase @Inject constructor(
    private val repository: OrdersRepository
) {

    operator fun invoke(): Flow<List<OrderItemAndProduct>> {
        return repository.getOrderItems()
    }
}
