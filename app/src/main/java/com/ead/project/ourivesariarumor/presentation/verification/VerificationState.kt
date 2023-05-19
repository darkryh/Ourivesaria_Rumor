package com.ead.project.ourivesariarumor.presentation.verification

sealed class VerificationState {
    object SendingVerificationUnSuccessFully : VerificationState()
    object SendingVerificationSuccessFully : VerificationState()
    object RequestVerificationUsed : VerificationState()
    object RequestVerificationUnused : VerificationState()
}
