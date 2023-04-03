package com.marcelocuevas.cabify.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marcelocuevas.cabify.domain.GetProductsUseCase
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

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