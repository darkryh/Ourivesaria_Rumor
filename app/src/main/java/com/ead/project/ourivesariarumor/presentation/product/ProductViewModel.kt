package com.ead.project.ourivesariarumor.presentation.product

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.presentation.product.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
) : ViewModel() {

    private val _quantity : MutableState<Int> = mutableStateOf(1)
    val quantity : State<Int> = _quantity

    private val _eventFlow : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val eventFlow : SharedFlow<UiEvent> = _eventFlow.asSharedFlow()


    fun onEvent(event: ProductEvent) {
        when(event) {
            ProductEvent.OnQuantityMinus -> {
                viewModelScope.launch {
                    val value = quantity.value - 1
                    if (value < 1) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("The quantity product can't be less than 1"))
                        return@launch
                    }
                    _quantity.value = value
                }
            }
            ProductEvent.OnQuantityPlus -> {
                _quantity.value = quantity.value + 1
            }
        }
    }
}