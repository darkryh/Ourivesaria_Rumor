package com.ead.project.ourivesariarumor.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders_table")
data class Order(
    @PrimaryKey(autoGenerate = false)
    val id : Long?=null,
    val userId : String,
    val productId : Long,
    val quantity : Int,
    val totalPrice : Double,
    val status : Int,
)
