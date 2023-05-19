package com.ead.project.ourivesariarumor.presentation.verification

import android.content.Context

sealed class VerificationEvent {
    data class SendEmailVerificationRequest(val context: Context) : VerificationEvent()
}
