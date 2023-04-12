package com.marcelocuevas.cabify.data.repository


interface CartRepository {

      suspend fun upsertOrderItem(productId: String, quantity: Int)
}
