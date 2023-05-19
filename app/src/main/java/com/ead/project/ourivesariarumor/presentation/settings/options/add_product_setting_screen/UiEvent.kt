package com.ead.project.ourivesariarumor.presentation.settings.options.add_product_setting_screen

import androidx.compose.material3.SnackbarDuration

sealed class UiEvent{
    data class ShowSnackBar(val message :String,val duration : SnackbarDuration = SnackbarDuration.Short) : UiEvent()
}
