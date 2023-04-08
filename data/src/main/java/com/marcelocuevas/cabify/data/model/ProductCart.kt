package com.marcelocuevas.cabify.data.model

data class ProductCart internal constructor(
    val code: String,
    val name: String,
    val price: String,
    val priceWithCurrency: String,
    val currency: String? = "€",
    val promotionDescription: String,
    val imageUrl: String,
    val quantity: Int,
) {
    constructor(product: Product, item: CartItem) : this(
        code = product.code,
        name = product.name,
        price = product.price,
        priceWithCurrency = product.priceWithCurrency,
        currency = product.currency,
        promotionDescription = product.promotionDescription,
        imageUrl = product.imageUrl,
        quantity = item.quantity
    )
}

fun List<Product>.mapToProductCart(item: CartItem): List<ProductCart> {
    return map { ProductCart(it, item) }
}