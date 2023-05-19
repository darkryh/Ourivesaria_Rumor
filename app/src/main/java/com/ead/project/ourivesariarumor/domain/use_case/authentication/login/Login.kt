package com.ead.project.ourivesariarumor.domain.use_case.authentication.login

import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import com.ead.project.ourivesariarumor.data.response.LoginResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Login @Inject constructor(private val authenticationService: AuthenticationService) {

    suspend operator fun invoke(email : String, password :String) : LoginResult =
        authenticationService.login(email, password)

}