package com.ead.project.ourivesariarumor.presentation.login.model

sealed class UiEvent {
    data class ShowSnackBar(val message : String) : UiEvent()
}

