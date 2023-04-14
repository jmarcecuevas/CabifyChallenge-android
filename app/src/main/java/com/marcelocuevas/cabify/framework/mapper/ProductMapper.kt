package com.marcelocuevas.cabify.framework.mapper

import com.marcelocuevas.cabify.data.api.ProductCodeDTO
import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.mapper.getImageUrl
import com.marcelocuevas.cabify.data.mapper.getPromotionDescription
import com.marcelocuevas.cabify.data.mapper.orZero
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
    promotionDescription = promotionDescription,
    imageUrl = imageUrl,
    quantity = quantity
)

fun ProductEntity.toDataModel() = Product(
    code = code,
    name = name,
    orderItemId = orderOwnerItemId,
    price = price,
    promotionDescription = promotionDescription,
    imageUrl = imageUrl,
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
    product = product?.toDataModel(),
    subtotal = subtotal
)

fun OrderItemAndProduct.toDataModel() = OrderItemAndProductEntity(
    orderItem = orderItem.toEntity(),
    product = product?.toEntity(),
    subtotal = subtotal
)

fun List<OrderItemAndProductEntity>.toDataModel(): List<OrderItemAndProduct> {
    val orders: MutableList<OrderItemAndProduct> = mutableListOf()
    forEach {
        val dataModel = it.toDataModel()
        orders.add(dataModel)
    }
    return orders
}
