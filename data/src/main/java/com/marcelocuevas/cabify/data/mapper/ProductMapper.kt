package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductCodeDTO
import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product

fun mapProductDto(input: ProductItemDTO) =
    Product(
        code = input.code.toString(),
        name = input.name.orEmpty(),
        price = input.price.orZero().toString(),
        priceWithCurrency = "${input.price.toString()} €",
        currency = "€",
        promotionDescription = getPromotionDescription(input.code),
        imageUrl = getImageUrl(input.code)
    )

fun getImageUrl(code: ProductCodeDTO?): String {
    code?.let { return when (code) {
        ProductCodeDTO.VOUCHER -> {
            "https://static.vecteezy.com/system/" +
                    "resources/previews/012/371/169/non_2x/" +
                    "greeting-card-or-discount-voucher-" +
                    "template-violet-background-vector.jpg"
        }
        ProductCodeDTO.T_SHIRT -> {
            "https://goofy-shannon-8fec5b.netlify.app/tshirt.jpg"
        }
        ProductCodeDTO.MUG -> {
            "https://goofy-shannon-8fec5b.netlify.app/mug.jpg"
        }
    }}
    return "https://jhm-images.images.sardius.media" +
            "/JHM/Shop/product_image_unavailable.png?width=260"

}

fun getPromotionDescription(code: ProductCodeDTO?): String {
    code?.let {
        return when (it) {
            ProductCodeDTO.T_SHIRT -> "2 x 1"
            ProductCodeDTO.VOUCHER -> "Up to 5% off"
            else -> {""}
        }
    }
    return ""
}
