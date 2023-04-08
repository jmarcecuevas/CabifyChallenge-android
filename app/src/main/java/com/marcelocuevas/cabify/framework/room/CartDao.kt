package com.marcelocuevas.cabify.framework.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert
    fun insertCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart_item")
    fun getCartItems(): Flow<List<CartItemEntity>>

    @Delete
    fun deleteCartItem(cartItem: CartItemEntity)

    @Query("UPDATE cart_item SET quantity=:quantity WHERE id=:id")
    fun updateQuantity(id: Int, quantity: Int)

    @Query("UPDATE cart_item SET totalItemPrice=:totalItemPrice WHERE id=:id")
    fun updatePrice(id: Int, totalItemPrice: Double)

    @Query("DELETE FROM cart_item")
    fun deleteAllItems()
}