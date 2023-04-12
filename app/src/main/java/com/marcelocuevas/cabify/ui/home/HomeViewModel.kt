package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.GetProductsUseCase
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.network.Result
import com.marcelocuevas.cabify.domain.UpdateCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@Immutable
sealed interface ProductsUiState {
    data class Success(val products: List<Product>) : ProductsUiState
    object Error : ProductsUiState
    object Loading : ProductsUiState
}

data class HomeUiState(
    val products: ProductsUiState,
    val isRefreshing: Boolean,
    val isError: Boolean
)

/**
 * A sealed hierarchy describing the state of the feed of news resources.
 */
sealed interface NewsFeedUiState {
    /**
     * The feed is still loading.
     */
    object Loading : NewsFeedUiState

    /**
     * The feed is loaded with the given list of news resources.
     */
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val products: List<Product>,
    ) : NewsFeedUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val updateCart: UpdateCartUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Result<List<Product>>>(Result.Loading())
    val uiState: StateFlow<Result<List<Product>>> = _uiState

    init {
        viewModelScope.launch {
            getProducts.invoke()
                .collect {
                    _uiState.value = it
                }
        }
    }

    fun onIncreaseItemClicked(code: String, newQuantity: Int) =
        viewModelScope.launch {
            updateCart.invoke(code, quantity = newQuantity)
        }

    fun onDecreaseItemCount(code: String) {
        print("asd")
    }
}
