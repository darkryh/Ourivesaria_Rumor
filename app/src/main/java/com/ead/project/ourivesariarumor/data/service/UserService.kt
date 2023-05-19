package com.ead.project.ourivesariarumor.data.service

import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.util.hashMap
import com.ead.project.ourivesariarumor.domain.model.users.UserSignIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val firebase: FirebaseClient
) {

    private val usersCollection by lazy {
        firebase.database.collection(USERS_PATH)
    }

    suspend fun createUserTable(userSignIn: UserSignIn) = runCatching {
        usersCollection
            .document(firebase.auth.currentUser?.uid?:return@runCatching false)
            .set(hashMap(userSignIn = userSignIn))
            .await()
    }.isSuccess

    companion object {
        const val USERS_PATH = "users"
    }
}