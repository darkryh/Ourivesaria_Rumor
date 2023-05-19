package com.ead.project.ourivesariarumor.domain.model

data class Order(
    val id : Long?=null,
    val userId : String,
    val productId : Long,
    val quantity : Int,
    val totalPrice : Double,
    val status : Int,
)
