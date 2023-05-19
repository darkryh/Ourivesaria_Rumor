package com.ead.project.ourivesariarumor.presentation.settings.options.add_product_setting_screen

import android.net.Uri
import androidx.compose.ui.focus.FocusState

sealed class AddProductEvent {
    data class SetProductPhotoUri(val uri: Uri?) : AddProductEvent()
    object AddNewProduct : AddProductEvent()
    data class EnteredProductName(val value : String) : AddProductEvent()
    data class ChangeProductNameFocus(val focusState: FocusState) : AddProductEvent()
    data class EnteredProductDescription(val value : String) : AddProductEvent()
    data class ChangeProductDescriptionFocus(val focusState: FocusState) : AddProductEvent()
    data class EnteredProductPrice(val value : String) : AddProductEvent()
    data class ChangeProductPriceFocus(val focusState: FocusState) : AddProductEvent()
    data class EnteredProductQuantity(val value : String) : AddProductEvent()
    data class ChangeProductQuantityFocus(val focusState: FocusState) : AddProductEvent()
}
