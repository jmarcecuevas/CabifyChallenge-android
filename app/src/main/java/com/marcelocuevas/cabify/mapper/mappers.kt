package com.marcelocuevas.cabify.mapper

import com.marcelocuevas.cabify.data.model.OrderItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.framework.room.entity.OrderItemAndProduct as OrderItemAndProductEntity
import com.marcelocuevas.cabify.data.model.OrderItemAndProduct
import com.marcelocuevas.cabify.framework.room.entity.OrderItemEntity
import com.marcelocuevas.cabify.framework.room.entity.ProductEntity

fun Product.toEntity() = ProductEntity(
    code = code,
    name = name,
    orderOwnerItemId = orderItemId,
    price = price,
    quantity = quantity
)

fun ProductEntity.toDataModel() = Product(
    code = code,
    name = name,
    orderItemId = orderOwnerItemId,
    price = price,
    quantity = quantity
)

fun OrderItemEntity.toDataModel() = OrderItem(
    productId = productId,
    quantity = quantity,
)

fun OrderItem.toEntity() = OrderItemEntity(
    productId = productId,
    quantity = quantity,
)

fun OrderItemAndProductEntity.toDataModel() = OrderItemAndProduct(
    orderItem = orderItem.toDataModel(),
    product = product.toDataModel(),
    subtotal = subtotal,
    total = 0.0,
    discountObtained = 0.0,
    hasDiscount = false
)

fun List<OrderItemAndProductEntity>.toDataModel(): List<OrderItemAndProduct> {
    val orders: MutableList<OrderItemAndProduct> = mutableListOf()
    forEach {
        val dataModel = it.toDataModel()
        orders.add(dataModel)
    }
    return orders
}
