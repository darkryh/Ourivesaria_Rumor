package com.ead.project.ourivesariarumor.domain.model.users

import java.time.LocalDate

data class UserSignIn(
    val email : String,
    val password : String,
    val name : String,
    val lastName : String,
    val phone : Int,
    val city : String,
    val birthDay : LocalDate,
    val address : String,
    val addressLine1 : String?,
    val addressLine2 : String?
)
