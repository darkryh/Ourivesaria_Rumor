package com.ead.project.ourivesariarumor.presentation.verification

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ead.project.ourivesariarumor.app.data.util.system.toMinutes
import com.ead.project.ourivesariarumor.domain.use_case.AuthenticationUseCase
import com.ead.project.ourivesariarumor.presentation.verification.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class VerificationViewModel @Inject constructor(
    private val authenticationUseCase: AuthenticationUseCase
) : ViewModel() {

    private val _uiEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val uiEvent : SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _verificationState : MutableState<VerificationState> =
        mutableStateOf(
            runBlocking {
                if (authenticationUseCase.checkEmailVerificationUsed())
                    VerificationState.RequestVerificationUsed
                else
                    VerificationState.RequestVerificationUnused
            }
        )
    val verificationState : State<VerificationState> = _verificationState

    val verifyAccount = authenticationUseCase.verifyAccount()

    init {
        viewModelScope.launch {
            restoreTimer()
        }
    }

    private suspend fun restoreTimer() {
        if (authenticationUseCase.checkEmailVerificationUsed()) {
            val remainingTime = getRemainingTime()
            if (remainingTime > 0) {
                delay(remainingTime)
                authenticationUseCase.updateCheckEmailVerificationUsed(false)
                _verificationState.value = VerificationState.RequestVerificationUnused
            } else {
                authenticationUseCase.updateCheckEmailVerificationUsed(false)
                _verificationState.value = VerificationState.RequestVerificationUnused
            }
        }
    }

    private suspend fun getRemainingTime() : Long {
        val elapsedTime = System.currentTimeMillis() - authenticationUseCase.timerVerification()
        return  5 * 60 * 1000 - elapsedTime
    }

    fun onEvent(event: VerificationEvent) {
        when(event) {
            is VerificationEvent.SendEmailVerificationRequest -> {
                viewModelScope.launch {
                    _verificationState.value = authenticationUseCase.sendVerification()
                    when(_verificationState.value) {
                        VerificationState.RequestVerificationUnused -> {
                            _uiEvent.emit(UiEvent.ShowSnackBar("Verification unused send verification with the button."))
                        }
                        VerificationState.RequestVerificationUsed -> {
                            _uiEvent.emit(UiEvent.ShowSnackBar("Email request was already used, wait ${getRemainingTime().toMinutes()} minutes."))
                        }
                        VerificationState.SendingVerificationSuccessFully -> {
                            _uiEvent.emit(UiEvent.ShowSnackBar("Email request successfully, check your email."))
                            restoreTimer()
                        }
                        VerificationState.SendingVerificationUnSuccessFully -> {
                            _uiEvent.emit(UiEvent.ShowSnackBar("Email request unsuccessfully, try again."))
                        }
                    }
                }
            }
        }
    }
}