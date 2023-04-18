package com.marcelocuevas.cabify.utils

import com.marcelocuevas.cabify.data.api.ProductCode
import com.marcelocuevas.cabify.data.model.Product

fun Product.imageUrl(): String {
    return when (code) {
        ProductCode.VOUCHER.name -> {
            "https://media.istockphoto.com/id/1286165036/vector/summer-gift-vouchers-template.jpg?s=612x612&w=0&k=20&c=ZNwRBcqVt_H7E12JhWCvyTm9_G2IUQNWpIWr4oqomo0="
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
