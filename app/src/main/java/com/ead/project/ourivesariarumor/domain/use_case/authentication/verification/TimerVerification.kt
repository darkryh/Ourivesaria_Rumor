package com.ead.project.ourivesariarumor.domain.use_case.authentication.verification

import android.content.Context
import androidx.datastore.preferences.core.longPreferencesKey
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.EMAIL_TIME
import com.ead.project.ourivesariarumor.domain.use_case.authentication.util.store
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TimerVerification @Inject constructor (private val context: Context) {

    suspend operator fun invoke() : Long {
        return context.store
            .data.first()[longPreferencesKey(EMAIL_TIME)]?:0L
    }

}