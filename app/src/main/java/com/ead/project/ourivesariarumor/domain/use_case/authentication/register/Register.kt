package com.ead.project.ourivesariarumor.domain.use_case.authentication.register

import com.ead.project.ourivesariarumor.data.service.AuthenticationService
import com.ead.project.ourivesariarumor.data.service.UserService
import com.ead.project.ourivesariarumor.domain.model.users.UserSignIn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Register @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
){

    suspend operator fun invoke(userSignIn: UserSignIn) : Boolean {
        val accountCreated =
            authenticationService.createAccount(userSignIn.email, userSignIn.password) != null

        return if (accountCreated) {
            userService
                .createUserTable(userSignIn = userSignIn)
        } else {
            false
        }
    }
}