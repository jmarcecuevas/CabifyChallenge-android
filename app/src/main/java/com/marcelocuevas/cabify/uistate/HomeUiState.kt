package com.marcelocuevas.cabify.uistate

import com.marcelocuevas.cabify.data.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val isBottomSheetExpanded: Boolean = false,
    val totalCount: Int = 0,
    val products: List<Product> = emptyList()
)

data class ProductItemUiState(
    val code: String,
    val name: String,
    val priceWithCurrency: String,
    val promotionDescription: String,
    val imageUrl: String,
    val currentCount: Int = 0
)
