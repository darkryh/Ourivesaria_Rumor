package com.ead.project.ourivesariarumor.presentation.register

import androidx.compose.ui.focus.FocusState
import java.time.LocalDate

sealed class RegisterEvent{
    data class EnteredEmail(val value : String) : RegisterEvent()
    data class ChangeEmailFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredPassword(val value : String) : RegisterEvent()
    data class ChangePasswordFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredName(val value : String) : RegisterEvent()
    data class ChangeNameFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredLastName(val value : String) : RegisterEvent()
    data class ChangeLastNameFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredPhone(val value : String) : RegisterEvent()
    data class ChangePhoneFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredCity(val value : String) : RegisterEvent()
    data class ChangeCityFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredAddress(val value : String) : RegisterEvent()
    data class ChangeAddressFocus(val focusState: FocusState) : RegisterEvent()
    data class EnteredAddressLine1(val value : String) : RegisterEvent()
    data class ChangeAddressLine1Focus(val focusState: FocusState) : RegisterEvent()
    data class EnteredAddressLine2(val value : String) : RegisterEvent()
    data class ChangeAddressLine2Focus(val focusState: FocusState) : RegisterEvent()
    data class EnteredAge(val value : LocalDate) : RegisterEvent()
    data class ChangeAgeFocus(val focusState: FocusState) : RegisterEvent()
    data class RegisterUser(val verifyAction : () -> Unit) : RegisterEvent()
}
