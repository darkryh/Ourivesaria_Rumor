package com.ead.project.ourivesariarumor.domain.model

data class Cart(
    val id : Long,
    val userId : String,
    val productId : Long,
    val quantity : Int,
    val totalPrice : Double
)
