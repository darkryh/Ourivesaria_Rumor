package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.EMAIL_VERIFIED
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.store
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckEmailVerificationUsed @Inject constructor(
    private val context: Context
) {

    suspend operator fun invoke() : Boolean  =
        context.store.data.first()[booleanPreferencesKey(EMAIL_VERIFIED)]?:false
}