package com.ead.project.ourivesariarumor.presentation.settings.options.add_product_setting_screen

import android.net.Uri
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.app.data.util.system.hasOnlyNumbers
import com.ead.project.ourivesariarumor.domain.model.Category
import com.ead.project.ourivesariarumor.domain.model.Currency
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.domain.use_case.InventoryUseCase
import com.ead.project.ourivesariarumor.presentation.main.model.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor (
    private val inventoryUseCase: InventoryUseCase
) : ViewModel() {

    private val _eventFlow : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val eventFlow : SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    private val _productPhotoUri : MutableState<Uri?> = mutableStateOf(null)
    val productPhotoUri : State<Uri?> = _productPhotoUri

    private val _productName : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "name of the product"
    ))
    val productName : State<TextFieldState> = _productName

    private val _productDescription : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "description of the product"
    ))
    val productDescription : State<TextFieldState> = _productDescription

    private val _productPrice : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "price of the product"
    ))
    val productPrice : State<TextFieldState> = _productPrice

    private val _productQuantity : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "initial quantity of the product"
    ))
    val productQuantity : State<TextFieldState> = _productQuantity

    private val _productCategories : MutableState<List<Pair<Long,String>>> = mutableStateOf(emptyList())
    val productCategories: State<List<Pair<Long,String>>> = _productCategories

    fun onEvent(event: AddProductEvent) {
        when(event) {
            is AddProductEvent.EnteredProductName -> {
                _productName.value = productName.value.copy(
                    text = event.value
                )
            }
            is AddProductEvent.EnteredProductDescription -> {
                _productDescription.value = productDescription.value.copy(
                    text = event.value
                )
            }
            is AddProductEvent.EnteredProductPrice -> {
                _productPrice.value = productPrice.value.copy(
                    text = event.value
                )
            }
            is AddProductEvent.EnteredProductQuantity -> {
                viewModelScope.launch {
                    if (!event.value.hasOnlyNumbers()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Numbers are only accepted."))
                        return@launch
                    }
                    _productQuantity.value = productQuantity.value.copy(
                        text = event.value
                    )
                }
            }
            is AddProductEvent.ChangeProductNameFocus -> {
                _productName.value = productName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            productName.value.text.isBlank()
                )
            }
            is AddProductEvent.ChangeProductDescriptionFocus -> {
                _productDescription.value = productDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            productDescription.value.text.isBlank()
                )
            }
            is AddProductEvent.ChangeProductPriceFocus -> {
                _productPrice.value = productPrice.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            productPrice.value.text.isBlank()
                )
            }
            is AddProductEvent.ChangeProductQuantityFocus -> {
                _productQuantity.value = productQuantity.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            productQuantity.value.text.isBlank()
                )
            }
            is AddProductEvent.SetProductPhotoUri -> {
                _productPhotoUri.value = event.uri
            }
            is AddProductEvent.AddNewProduct -> {
                viewModelScope.launch {

                    val productName = _productName.value.text
                    val productDescription = _productDescription.value.text
                    val productPrice = _productPrice.value.text.ifBlank { "0.0" }.toDouble()
                    val productQuantity = _productQuantity.value.text.ifBlank { "0" }.toInt()
                    val productPhotoUri = _productPhotoUri.value

                    if (productName.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Needs product name"))
                        return@launch
                    }
                    if (productDescription.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Needs product description"))
                        return@launch
                    }
                    if (productPrice == 0.0) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("product price cannot be 0"))
                        return@launch
                    }
                    if (productQuantity == 0) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("product quantity cannot be 0"))
                        return@launch
                    }

                    if (productPhotoUri == null) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Needs a photo of the product"))
                        return@launch
                    }

                    val product = Product(
                        id = null,
                        name = productName,
                        description = productDescription,
                        image = null,
                        price = productPrice,
                        quantity = productQuantity,
                        currency = Currency.EUR,
                        categories = listOf(
                            Category(
                                id = 1,
                                "category 1",
                                "description 1"
                            ),
                            Category(
                                id = 2,
                                "category 2",
                                "description 2"
                            ),
                            Category(
                                id = 3,
                                "category 3",
                                "description 3"
                            )
                        ),
                        requestedTimes = 0,
                        popularityPoints = 0f
                    )

                    _eventFlow.emit(UiEvent.ShowSnackBar(
                        message = "Uploading Product..",
                        duration = SnackbarDuration.Indefinite
                    ))

                    if (inventoryUseCase.addProduct(product, productPhotoUri)) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("The uploading is correct"))
                    }
                    else {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Error uploading product"))
                    }
                }
            }
        }
    }
}