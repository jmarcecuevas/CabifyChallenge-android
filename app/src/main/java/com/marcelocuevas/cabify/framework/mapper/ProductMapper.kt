package com.marcelocuevas.cabify.framework.mapper

import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.room.entity.OrderItemEntity
import com.marcelocuevas.cabify.framework.room.entity.ProductEntity

fun Product.toEntity() = ProductEntity(
    code = code,
    name = name,
    orderItemId = orderItemId,
    price = price,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)

fun ProductEntity.toDataModel() = Product(
    code = code,
    name = name,
    orderItemId = orderItemId,
    price = price,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl
)

fun OrderItemEntity.toDataModel() = OrderItem(
    productId = productId,
    quantity = quantity,
    subtotal = subtotal
)

fun OrderItem.toEntity() = OrderItemEntity(
    productId = productId,
    quantity = quantity,
    subtotal = subtotal
)

//fun CartItemEntity.toDataModel() = CartItem(
//    //id = id,
//    code = code,
//    name = name,
//    price = price,
//    currency = currency,
//    imageUrl = imageUrl,
//    quantity = quantity,
//    totalItemPrice = totalItemPrice
//)
//
//fun CartItem.toEntity() = CartItemEntity(
//    //id = id,
//    code = code,
//    name = name,
//    price = price,
//    currency = currency,
//    imageUrl = imageUrl,
//    quantity = quantity,
//    totalItemPrice = totalItemPrice
//)
