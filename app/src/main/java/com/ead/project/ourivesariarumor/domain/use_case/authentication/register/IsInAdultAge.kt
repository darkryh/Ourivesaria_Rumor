package com.ead.project.ourivesariarumor.domain.use_case.authentication.register

import java.time.LocalDate
import java.time.Period
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IsInAdultAge @Inject constructor() {

    operator fun invoke(birthDate: LocalDate): Boolean {
        val today = LocalDate.now()
        val age = Period.between(birthDate, today)
        return age.years >= 18
    }
}