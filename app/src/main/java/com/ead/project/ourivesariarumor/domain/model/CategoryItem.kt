package com.ead.project.ourivesariarumor.domain.model

import androidx.annotation.DrawableRes

data class CategoryItem(
    val title : String,
    @DrawableRes val drawableRes: Int,
    val contentDescription : String
)