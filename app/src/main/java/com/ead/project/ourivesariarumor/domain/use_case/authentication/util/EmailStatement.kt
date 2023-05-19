package com.ead.project.ourivesariarumor.domain.use_case.authentication.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

internal const val EMAIL_STATEMENT = "EMAIL_STATEMENT"
internal val Context.store : DataStore<Preferences> by preferencesDataStore(
    EMAIL_STATEMENT
)
internal const val EMAIL_VERIFIED = "EMAIL_VERIFIED"
internal const val EMAIL_TIME = "EMAIL_TIME"