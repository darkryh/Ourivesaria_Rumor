package com.ead.project.ourivesariarumor.presentation.login

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.app.data.util.system.launchActivityAndFinish
import com.ead.project.ourivesariarumor.app.data.util.system.toast
import com.ead.project.ourivesariarumor.data.response.LoginResult
import com.ead.project.ourivesariarumor.domain.use_case.AuthenticationUseCase
import com.ead.project.ourivesariarumor.presentation.login.model.LoginState
import com.ead.project.ourivesariarumor.presentation.login.model.UiEvent
import com.ead.project.ourivesariarumor.presentation.main.MainActivity
import com.ead.project.ourivesariarumor.presentation.main.model.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    context : Context,
    private val authenticationUseCase: AuthenticationUseCase
)  : ViewModel() {

    private val _eventFlow : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val eventFlow : SharedFlow<UiEvent> = _eventFlow.asSharedFlow()

    private val _emailState : MutableState<LoginState> = mutableStateOf(LoginState.EmailDefault)
    val emailState : State<LoginState> = _emailState

    private val _passwordState : MutableState<LoginState> = mutableStateOf(LoginState.PasswordDefault)
    val passwordState : State<LoginState> = _passwordState

    private val _email = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.email_hint)
    ))

    val email : State<TextFieldState> = _email

    private val _password = mutableStateOf(TextFieldState(
        hint = context.getString(R.string.password_hint)
    ))

    val password : State<TextFieldState> = _password

    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.OnBackEmail -> {
                _emailState.value = LoginState.EmailDefault
            }
            is LoginEvent.EnteredEmail -> {
                _email.value = email.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = password.value.copy(
                    text = event.value
                )
            }
            is LoginEvent.ChangeEmailFocus -> {
                _email.value = email.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            email.value.text.isBlank()
                )
            }
            is LoginEvent.ChangePasswordFocus -> {
                _password.value = password.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            password.value.text.isBlank()
                )
            }
            is LoginEvent.VerifyEmail -> {
                viewModelScope.launch {
                    val mEmail = email.value.text
                    if (mEmail.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Email needs to be provided"))
                        return@launch
                    }
                    _emailState.value = LoginState.EmailRequest
                    if (authenticationUseCase.verifyEmail(email = mEmail)) {
                        _emailState.value = LoginState.EmailCorrect
                    }
                    else {
                        _emailState.value = LoginState.EmailIncorrect
                    }
                }
            }
            is LoginEvent.VerifyUser -> {
                viewModelScope.launch {
                    val mEmail = email.value.text.trim()
                    val mPassword = password.value.text.trim()
                    if (mEmail.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Email needs to be provided"))
                        return@launch
                    }
                    if (mPassword.isBlank()) {
                        _eventFlow.emit(UiEvent.ShowSnackBar("Password needs to be provided"))
                        return@launch
                    }
                    _passwordState.value = LoginState.PasswordRequest

                    when(val result = authenticationUseCase.login(mEmail,mPassword)) {
                        is LoginResult.Success -> {
                            if (result.verified) {
                                event.context.launchActivityAndFinish(MainActivity::class.java)
                            }
                            else {
                                event.goToVerifyAction()
                                event.context.toast("Verify your email, to get access")
                            }
                            _passwordState.value = LoginState.PasswordCorrect
                        }
                        is LoginResult.Error -> {
                            _passwordState.value = LoginState.PasswordIncorrect
                            _eventFlow.emit(UiEvent.ShowSnackBar("Password is incorrect"))
                        }
                    }
                }
            }
        }
    }
}