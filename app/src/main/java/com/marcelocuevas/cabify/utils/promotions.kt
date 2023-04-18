package com.marcelocuevas.cabify.utils

import com.marcelocuevas.cabify.data.api.ProductCode
import com.marcelocuevas.cabify.data.model.Product

fun Product.imageUrl(): String {
    return when (code) {
        ProductCode.VOUCHER.name -> {
            "https://www.originalprofesional.com.ar/img/articulos/2020/12/gift_card_gold_para_regalar_1_imagen1.jpg"
        }
        ProductCode.TSHIRT.name -> {
            "https://goofy-shannon-8fec5b.netlify.app/tshirt.jpg"
        }
        ProductCode.MUG.name -> {
            "https://goofy-shannon-8fec5b.netlify.app/mug.jpg"
        }
        else -> {
            "https://jhm-images.images.sardius.media" +
                    "/JHM/Shop/product_image_unavailable.png?width=260"
        }
    }
}

fun Product.promotionDescription(): String {
    return when (code) {
        ProductCode.VOUCHER.name -> "Buy two, pay one"
        ProductCode.TSHIRT.name -> "5% off buying 3+"
        else -> { "" }
    }
}
