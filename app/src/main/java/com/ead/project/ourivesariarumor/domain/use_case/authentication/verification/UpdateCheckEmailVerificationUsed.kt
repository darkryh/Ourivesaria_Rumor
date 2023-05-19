package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.EMAIL_VERIFIED
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateCheckEmailVerificationUsed @Inject constructor (private val context : Context) {

    suspend operator fun invoke(value : Boolean) {
        context.store.edit { preferences ->
            preferences[booleanPreferencesKey(EMAIL_VERIFIED)] = value
        }
    }
}