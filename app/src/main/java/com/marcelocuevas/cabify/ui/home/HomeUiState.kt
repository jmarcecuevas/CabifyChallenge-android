package com.marcelocuevas.cabify.ui.home

import com.marcelocuevas.cabify.data.model.Product
import javax.annotation.concurrent.Immutable

@Immutable
sealed interface HomeUiState {
    object Loading : HomeUiState

    data class Success(
        val showOrderView: Boolean = false,
        val itemsAddedQuantity: Int,
        val subtotal: Double,
        val products: List<Product>,
    ) : HomeUiState
}
