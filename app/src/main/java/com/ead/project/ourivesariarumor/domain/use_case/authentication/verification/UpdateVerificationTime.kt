package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.EMAIL_TIME
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.store
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UpdateVerificationTime @Inject constructor (private val context : Context) {

    suspend operator fun invoke(value : Long) {
        context.store.edit { preferences ->
            preferences[longPreferencesKey(EMAIL_TIME)] = value
        }
    }
}