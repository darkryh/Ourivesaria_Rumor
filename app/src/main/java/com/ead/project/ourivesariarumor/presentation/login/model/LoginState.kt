package com.ead.project.ourivesariarumor.presentation.login.model

sealed class LoginState {
    object EmailDefault : LoginState()
    object EmailRequest : LoginState()
    object EmailCorrect : LoginState()
    object EmailIncorrect : LoginState()
    object PasswordDefault : LoginState()
    object PasswordRequest : LoginState()
    object PasswordCorrect : LoginState()
    object PasswordIncorrect : LoginState()
}
