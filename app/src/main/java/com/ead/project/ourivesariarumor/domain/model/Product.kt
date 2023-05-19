package com.ead.project.ourivesariarumor.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id : Long? = null,
    val name : String,
    val description : String,
    val image : String?,
    val price : Double,
    val quantity : Int,
    val currency : Currency = Currency.EUR,
    val categories : List<Pair<Long,String>>
) : Parcelable


