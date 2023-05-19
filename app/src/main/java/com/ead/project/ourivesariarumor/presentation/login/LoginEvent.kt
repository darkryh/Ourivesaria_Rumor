package com.ead.project.ourivesariarumor.presentation.login

import android.content.Context
import androidx.compose.ui.focus.FocusState

sealed class LoginEvent {
    data class EnteredEmail(val value : String) : LoginEvent()
    data class ChangeEmailFocus(val focusState: FocusState) : LoginEvent()
    data class EnteredPassword(val value : String) : LoginEvent()
    data class ChangePasswordFocus(val focusState: FocusState) : LoginEvent()
    object VerifyEmail : LoginEvent()
    data class VerifyUser(val context: Context,val goToVerifyAction : () -> Unit) : LoginEvent()
    object OnBackEmail : LoginEvent()
}

