package com.ead.project.ourivesariarumor.presentation.product.model

sealed class UiEvent{
    data class ShowSnackBar(val message : String) : UiEvent()
}
