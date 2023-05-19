package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyAccount @Inject constructor(private val authenticationService: AuthenticationService) {

    operator fun invoke() : Flow<Boolean> =
        authenticationService.verifiedAccount
}