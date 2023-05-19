package com.ead.project.ourivesariarumor.domain.model.sellers

import java.time.LocalDate

data class Seller(
    val id : String,
    val email : String,
    val profileImage : String,
    val name : String,
    val lastName : String,
    val phone : Int,
    val city : String,
    val birthDay : LocalDate,
    val address : String,
    val addressLine1 : String?,
    val addressLine2 : String?
)
