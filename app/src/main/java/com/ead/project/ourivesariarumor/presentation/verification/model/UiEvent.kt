package com.ead.project.ourivesariarumor.presentation.verification.model

sealed class UiEvent {
    data class ShowSnackBar(val message : String) : UiEvent()
}
