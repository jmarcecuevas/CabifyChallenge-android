package com.marcelocuevas.cabify.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.GetProductsUseCase
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.uistate.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUsecase: GetProductsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = HomeUiState(isLoading = true)
            getProductsUsecase()
                .flowOn(Dispatchers.IO)
                .catch { e ->

                }
                .collect {
                    val homeUiState = HomeUiState(
                        isLoading = false,
                        products = it
                    )
                    _uiState.value = homeUiState
                }
        }
    }
}