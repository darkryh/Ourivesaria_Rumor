package com.ead.project.ourivesariarumor.presentation.register.model


sealed class UiEvent {
    data class ShowSnackBar(val message : String) : UiEvent()
}
