package com.ead.project.ourivesariarumor.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "carts_table")
data class Cart(
    @PrimaryKey(autoGenerate = false)
    val id : Long,
    val userId : String,
    val productId : Long,
    val quantity : Int,
    val totalPrice : Double
)
