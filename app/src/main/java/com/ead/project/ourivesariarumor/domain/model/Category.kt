package com.ead.project.ourivesariarumor.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id : Long,
    val title : String,
    val description : String
) : Parcelable
