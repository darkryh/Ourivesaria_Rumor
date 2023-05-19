package com.ead.project.ourivesariarumor.domain.use_case.authentication

import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VerifyEmail @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email : String) : Boolean =
        authenticationService.verifyEmail(email)

}