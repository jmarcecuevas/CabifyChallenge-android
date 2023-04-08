package com.marcelocuevas.cabify.data.datasource

import com.marcelocuevas.cabify.data.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartDataSource {

    fun insertCartItem(item: CartItem)

    fun getCartItems(): Flow<List<CartItem>>

    fun deleteCartItem(item: CartItem)

    fun updateQuantity(id: Int, quantity: Int)

    fun updatePrice(id: Int, totalItemPrice: Double )

    fun deleteAllItems()
}