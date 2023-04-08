package com.marcelocuevas.cabify.data.api

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    val products: List<ProductItemDTO>? = emptyList()
)

data class ProductItemDTO(
    val code: ProductCodeDTO?,
    val name: String?,
    val price: Float?
)

enum class ProductCodeDTO{
    VOUCHER,
    @SerializedName("TSHIRT")
    T_SHIRT,
    MUG
}