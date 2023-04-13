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
    imageUrl = imageUrl
)

fun ProductEntity.toDataModel() = Product(
    code = code,
    name = name,
    orderItemId = orderOwnerItemId,
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

fun OrderItemAndProductEntity.toDataModel() = OrderItemAndProduct(
    orderItem = orderItem.toDataModel(),
    product = product?.toDataModel()
)

fun OrderItemAndProduct.toDataModel() = OrderItemAndProductEntity(
    orderItem = orderItem.toEntity(),
    product = product!!.toEntity()
)

fun List<OrderItemAndProductEntity>.toDataModel(): List<OrderItemAndProduct> {
    val orders: MutableList<OrderItemAndProduct> = mutableListOf()
    forEach {
        val dataModel = it.toDataModel()
        orders.add(dataModel)
    }
    return orders
}

//fun ProductItemDTO.toDataModel(): Product =
//    Product(
//        code = code.toString(),
//        name = name.orEmpty(),
//        price = price.orZero(),
//        currency = "€",
//        promotionDescription = getPromotionDescription(code),
//        imageUrl = getImageUrl(code)
//    )

//fun List<ProductItemDTO>.toDataModel(): List<Product> {
//    val products: MutableList<Product> = mutableListOf()
//    forEach {
//        val dataModel = it.toDataModel()
//        products.add(dataModel)
//    }
//    return products
//}
//
//fun getImageUrl(code: ProductCodeDTO?): String {
//    code?.let { return when (code) {
//        ProductCodeDTO.VOUCHER -> {
//            "https://static.vecteezy.com/system/" +
//                    "resources/previews/012/371/169/non_2x/" +
//                    "greeting-card-or-discount-voucher-" +
//                    "template-violet-background-vector.jpg"
//        }
//        ProductCodeDTO.T_SHIRT -> {
//            "https://goofy-shannon-8fec5b.netlify.app/tshirt.jpg"
//        }
//        ProductCodeDTO.MUG -> {
//            "https://goofy-shannon-8fec5b.netlify.app/mug.jpg"
//        }
//    }}
//    return "https://jhm-images.images.sardius.media" +
//            "/JHM/Shop/product_image_unavailable.png?width=260"
//
//}
//
//fun getPromotionDescription(code: ProductCodeDTO?): String {
//    code?.let {
//        return when (it) {
//            ProductCodeDTO.T_SHIRT -> "2 x 1"
//            ProductCodeDTO.VOUCHER -> "Up to 5% off"
//            else -> {""}
//        }
//    }
//    return ""
//}