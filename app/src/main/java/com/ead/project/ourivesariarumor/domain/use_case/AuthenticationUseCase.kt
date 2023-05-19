package com.ead.project.ourivesariarumor.domain.use_case

import com.ead.project.ourivesariarumor.domain.use_case.authentication.VerifyEmail
import com.ead.project.ourivesariarumor.domain.use_case.authentication.login.Login
import com.ead.project.ourivesariarumor.domain.use_case.authentication.register.IsInAdultAge
import com.ead.project.ourivesariarumor.domain.use_case.authentication.register.Register
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.CheckEmailVerificationUsed
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.SendVerification
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.TimerVerification
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.UpdateCheckEmailVerificationUsed
import com.ead.project.ourivesariarumor.domain.use_case.authentication.verification.VerifyAccount

data class AuthenticationUseCase(
    val login: Login,
    val register: Register,
    val sendVerification: SendVerification,
    val verifyAccount: VerifyAccount,
    val verifyEmail: VerifyEmail,
    val isInAdultAge: IsInAdultAge,
    val checkEmailVerificationUsed: CheckEmailVerificationUsed,
    val updateCheckEmailVerificationUsed: UpdateCheckEmailVerificationUsed,
    val timerVerification: TimerVerification
)
