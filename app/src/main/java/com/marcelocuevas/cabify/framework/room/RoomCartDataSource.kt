package com.marcelocuevas.cabify.framework.room

import com.marcelocuevas.cabify.data.datasource.CartDataSource
import com.marcelocuevas.cabify.data.model.CartItem
import com.marcelocuevas.cabify.framework.mapper.toDataModel
import com.marcelocuevas.cabify.framework.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomCartDataSource @Inject constructor(
    private val cartDao: CartDao
): CartDataSource{

    override fun insertCartItem(item: CartItem) =
        cartDao.insertCartItem(item.toEntity())

    override fun getCartItems(): Flow<List<CartItem>> =
        cartDao
            .getCartItems()
            .map { cartItemEntities ->
                cartItemEntities.map { it.toDataModel() }
            }

    override fun deleteCartItem(item: CartItem) =
        cartDao.deleteCartItem(item.toEntity())

    override fun updateQuantity(id: Int, quantity: Int) =
        cartDao.updateQuantity(id, quantity)

    override fun updatePrice(id: Int, totalItemPrice: Double) =
        cartDao.updatePrice(id, totalItemPrice)

    override fun deleteAllItems() =
        cartDao.deleteAllItems()
}
