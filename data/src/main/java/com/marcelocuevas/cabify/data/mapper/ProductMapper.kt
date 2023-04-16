package com.marcelocuevas.cabify.data.mapper

import com.marcelocuevas.cabify.data.api.ProductCode
import com.marcelocuevas.cabify.data.api.ProductItemDTO
import com.marcelocuevas.cabify.data.model.Product

fun mapProductDto(input: ProductItemDTO) =
    Product(
        code = input.code!!,
        name = input.name.orEmpty(),
        price = input.price.orZero(),
        currency = "€",
        promotionDescription = getPromotionDescription(input.code),
        imageUrl = getImageUrl(input.code)
    )

fun getImageUrl(code: ProductCode?): String {
    code?.let { return when (code) {
        ProductCode.VOUCHER -> {
            "https://static.vecteezy.com/system/" +
                    "resources/previews/012/371/169/non_2x/" +
                    "greeting-card-or-discount-voucher-" +
                    "template-violet-background-vector.jpg"
        }
        ProductCode.T_SHIRT -> {
            "https://goofy-shannon-8fec5b.netlify.app/tshirt.jpg"
        }
        ProductCode.MUG -> {
            "https://goofy-shannon-8fec5b.netlify.app/mug.jpg"
        }
    }}
    return "https://jhm-images.images.sardius.media" +
            "/JHM/Shop/product_image_unavailable.png?width=260"

}

fun getPromotionDescription(code: ProductCode?): String {
    code?.let {
        return when (it) {
            ProductCode.T_SHIRT -> "2 x 1"
            ProductCode.VOUCHER -> "Up to 5% off"
            else -> {""}
        }
    }
    return ""
}
