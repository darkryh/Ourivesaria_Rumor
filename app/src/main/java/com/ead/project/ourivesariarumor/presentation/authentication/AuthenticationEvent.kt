package com.ead.project.ourivesariarumor.presentation.authentication

sealed class AuthenticationEvent {
    data class EnteredEmail(val value : String) : AuthenticationEvent()
}
