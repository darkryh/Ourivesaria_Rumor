package com.ead.project.ourivesariarumor.presentation.register

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.app.data.util.system.hasOnlyNumbers
import com.ead.project.ourivesariarumor.domain.model.users.UserSignIn
import com.ead.project.ourivesariarumor.domain.use_case.AuthenticationUseCase
import com.ead.project.ourivesariarumor.presentation.main.model.TextFieldState
import com.ead.project.ourivesariarumor.presentation.register.model.RegisterState
import com.ead.project.ourivesariarumor.presentation.register.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    context: Context,
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

    private val emailRegex = Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@(?:[a-zA-Z0-9-]+\\.)+(com|net|org|edu|gov|mil|biz|info|name|museum|us|ca|uk|au|eu|asia|tv)$")
    private val phoneRegex = Regex("[0-9]{9,}")

    private val _eventFlow : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val eventFlow : SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    private val _emailState : MutableState<RegisterState> = mutableStateOf(RegisterState.EmailDefault)
    val emailState : State<RegisterState> = _emailState

    private val _email = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.email_hint)
    ))

    val email : State<TextFieldState> = _email

    private val _password = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.password_hint)
    ))

    val password : State<TextFieldState> = _password

    private val _name = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.name_hint)
    ))

    val name : State<TextFieldState> = _name

    private val _lastName = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.last_name_hint)
    ))

    val lastName : State<TextFieldState> = _lastName

    private val _phone : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "Phone"
    ))
    val phone : State<TextFieldState> = _phone

    private val _city : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "City"
    ))
    val city : State<TextFieldState> = _city

    private val _address : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "Address"
    ))
    val address : State<TextFieldState> = _address

    private val _addressLine1 : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
        hint = "Address line 1 (Optional)"
    ))
    val addressLine1 : State<TextFieldState> = _addressLine1

    private val _addressLine2 : MutableState<TextFieldState> = mutableStateOf(TextFieldState(
            hint = "Address line 2 (Optional)"
    ))
    val addressLine2 : State<TextFieldState> = _addressLine2

    private val _birthDay : MutableState<LocalDate?> = mutableStateOf(null)
    val birthDay : State<LocalDate?> = _birthDay

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EnteredEmail -> {
                viewModelScope.launch {
                    _email.value = email.value.copy(
                        text = event.value
                    )
                    if (emailRegex.matches(event.value)) {
                        _emailState.value = RegisterState.EmailRequest
                        if (authenticationUseCase.verifyEmail(event.value)) {
                            _eventFlow.emit(UiEvent.ShowSnackBar("email already registered"))
                            _emailState.value = RegisterState.EmailNotAvailable
                        }
                        else {
                            _emailState.value = RegisterState.EmailAvailable
                        }
                    }
                    else {
                        _emailState.value = RegisterState.EmailDefault
                    }
                }
            }
            is RegisterEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredName -> {
                _name.value = name.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredLastName -> {
                _lastName.value = lastName.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredPhone -> {
                viewModelScope.launch {
                    if (!event.value.hasOnlyNumbers()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Numbers are only accepted."))
                        return@launch
                    }
                    _phone.value = phone.value.copy(
                        text = event.value
                    )
                }
            }
            is RegisterEvent.EnteredCity -> {
                _city.value = city.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredAddress -> {
                _address.value = address.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredAddressLine1 -> {
                _addressLine1.value = addressLine1.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredAddressLine2 -> {
                _addressLine2.value = addressLine2.value.copy(
                    text = event.value
                )
            }
            is RegisterEvent.EnteredAge -> {
                _birthDay.value = event.value
            }
            is RegisterEvent.ChangeEmailFocus -> {
                _email.value = email.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            email.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangePasswordFocus -> {
                _password.value = password.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            password.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeNameFocus -> {
                _name.value = name.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            name.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeLastNameFocus -> {
                _lastName.value = lastName.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            lastName.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangePhoneFocus -> {
                _phone.value = phone.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            phone.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeCityFocus -> {
                _city.value = city.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            city.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeAddressFocus -> {
                _address.value = address.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            address.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeAddressLine1Focus -> {
                _addressLine1.value = addressLine1.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            addressLine1.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeAddressLine2Focus -> {
                _addressLine2.value = addressLine2.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            addressLine2.value.text.isBlank()
                )
            }
            is RegisterEvent.ChangeAgeFocus -> { event.focusState.isFocused }
            is RegisterEvent.RegisterUser -> {
                viewModelScope.launch {
                    val mEmail = email.value.text.trim()
                    val mPassword = password.value.text.trim()
                    val mName = name.value.text.trim()
                    val mLastName = lastName.value.text.trim()
                    val mPhone = phone.value.text.trim().ifBlank { "-1" }.toInt()
                    val mCity = city.value.text.trim()
                    val mAddress = address.value.text.trim()
                    val mBirthDay = birthDay.value
                    val emailMatches = emailRegex.matches(mEmail)
                    val mAddressLine1 = addressLine1.value.text.trim()
                    val mAddressLine2 = addressLine2.value.text.trim()

                    if (mName.isBlank() || mName.length < 3){
                        _eventFlow.emit(UiEvent.ShowSnackBar("name needs to be provided"))
                        return@launch
                    }
                    if (mLastName.isBlank() || mLastName.length < 3){
                        _eventFlow.emit(UiEvent.ShowSnackBar("last name needs to be provided"))
                        return@launch
                    }
                    if (!phoneRegex.matches(mPhone.toString())) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("phone number structure incorrect"))
                        return@launch
                    }
                    if (mAddress.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("address needs to be provided"))
                        return@launch
                    }
                    if (mBirthDay == null) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("birthday needs to be provided"))
                        return@launch
                    }
                    else {
                        if (!authenticationUseCase.isInAdultAge(mBirthDay)) {
                            _eventFlow.emit(UiEvent.ShowSnackBar("your age is under 18 years old"))
                            return@launch
                        }
                    }
                    if (mPassword.isBlank() && mPassword.length < 4){
                        _eventFlow.emit(UiEvent.ShowSnackBar("password needs to be provided"))
                        return@launch
                    }
                    if (mEmail.isBlank() || !emailMatches) {
                        if (mEmail.isBlank()) {
                            _eventFlow.emit(UiEvent.ShowSnackBar("email needs to be provided"))
                        }
                        else{
                            _eventFlow.emit(UiEvent.ShowSnackBar("email format not correct"))
                        }
                        return@launch
                    }

                    if (authenticationUseCase.register(userSignIn =
                            UserSignIn(
                                email = mEmail,
                                password = mPassword,
                                name = mName,
                                lastName = mLastName,
                                phone = mPhone,
                                birthDay = mBirthDay,
                                city = mCity,
                                address = mAddress,
                                addressLine1 = mAddressLine1.ifBlank { null },
                                addressLine2 = mAddressLine2.ifBlank { null }
                            ))
                    ) {
                        event.verifyAction()
                    }
                    else {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Error registering, verify data"))
                    }
                }
            }
        }
    }
}