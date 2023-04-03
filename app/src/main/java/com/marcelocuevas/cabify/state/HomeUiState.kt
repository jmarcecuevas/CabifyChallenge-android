package com.marcelocuevas.cabify.state

import com.marcelocuevas.cabify.data.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList()
)
