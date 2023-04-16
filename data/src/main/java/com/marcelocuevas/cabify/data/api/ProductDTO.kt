package com.marcelocuevas.cabify.data.api

data class ProductDTO(
    val products: List<ProductItemDTO>? = emptyList()
)

data class ProductItemDTO(
    val code: String?,
    val name: String?,
    val price: Double?
)

enum class ProductCode{
    VOUCHER,
    TSHIRT,
    MUG
}