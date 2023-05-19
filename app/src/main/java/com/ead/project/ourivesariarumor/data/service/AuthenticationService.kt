package com.ead.project.ourivesariarumor.data.service

import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.response.LoginResult
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {

    val verifiedAccount : Flow<Boolean> = flow {
        while (true) {
            val isVerified = verifyEmailIsVerified()
            emit(isVerified)
            delay(1000)
        }
    }

    suspend fun verifyEmail(email: String) = (runCatching {
        firebase.database.collection("users").whereEqualTo("email", email).get().await()
    }.getOrNull()?.documents?.size ?: 0) > 0

    suspend fun login(email : String, password : String) = runCatching {
        firebase.auth.signInWithEmailAndPassword(email,password).await()
    }.toLoginResult()

    suspend fun createAccount(email: String, password: String) : AuthResult?  {
        return firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun sendEmailVerification() = runCatching {
        firebase.auth.currentUser?.sendEmailVerification()?.await()
    }.isSuccess

    private suspend fun verifyEmailIsVerified() : Boolean  {
        firebase.auth.currentUser?.reload()?.await()
        return firebase.auth.currentUser?.isEmailVerified?:false
    }

    private fun Result<AuthResult>.toLoginResult() : LoginResult {
        return when(val result = getOrNull()) {
            null -> LoginResult.Error
            else -> {
                val userId = result.user
                checkNotNull(userId)
                LoginResult.Success(result.user?.isEmailVerified?:false)
            }
        }
    }
}