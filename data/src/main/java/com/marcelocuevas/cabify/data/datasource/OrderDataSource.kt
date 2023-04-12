package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.OrderItem

interface OrderDataSource {

      suspend fun upsertOrderItem(item: OrderItem): Long
}
