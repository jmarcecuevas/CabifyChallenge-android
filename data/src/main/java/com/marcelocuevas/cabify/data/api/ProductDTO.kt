package com.marcelocuevas.cabify.data.api

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    val products: List<ProductItemDTO>? = emptyList()
)

data class ProductItemDTO(
    val code: ProductCode?,
    val name: String?,
    val price: Double?
)

enum class ProductCode{
    VOUCHER,
    @SerializedName("TSHIRT")
    T_SHIRT,
    MUG
}