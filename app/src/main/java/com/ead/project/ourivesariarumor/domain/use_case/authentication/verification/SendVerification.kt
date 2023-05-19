package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import com.ead.project.ourivesariarumor.presentation.verification.VerificationState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SendVerification @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val checkEmailVerificationUsed: CheckEmailVerificationUsed,
    private val updateCheckEmailVerificationUsed: UpdateCheckEmailVerificationUsed,
    private val updateVerificationTime: UpdateVerificationTime
    ) {

    suspend operator fun invoke() : VerificationState {
        if (!checkEmailVerificationUsed()) {
            if (authenticationService.sendEmailVerification()) {
                val verificationTimeRequest = System.currentTimeMillis()
                updateVerificationTime(verificationTimeRequest)
                updateCheckEmailVerificationUsed(true)
                return VerificationState.SendingVerificationSuccessFully
            }
            return VerificationState.SendingVerificationUnSuccessFully
        }
        return VerificationState.RequestVerificationUsed
    }
}