package com.ead.project.ourivesariarumor.presentation.register.model

sealed class RegisterState {
    object EmailDefault : RegisterState()
    object EmailRequest : RegisterState()
    object EmailAvailable : RegisterState()
    object EmailNotAvailable : RegisterState()
}
