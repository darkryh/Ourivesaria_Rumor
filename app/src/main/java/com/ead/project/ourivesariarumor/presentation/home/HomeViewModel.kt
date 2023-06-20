package com.ead.project.ourivesariarumor.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.domain.use_case.InventoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val inventoryUseCase: InventoryUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        fetchingProducts()
    }

    private fun fetchingProducts() {
        viewModelScope.launch {
            _products.value = inventoryUseCase.getProducts()
        }
    }
}