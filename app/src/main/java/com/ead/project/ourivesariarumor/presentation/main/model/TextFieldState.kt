package com.ead.project.ourivesariarumor.presentation.main.model

import com.ead.project.ourivesariarumor.presentation.util.EmptyString

data class TextFieldState(
    var text : String = EmptyString,
    val hint : String,
    val isHintVisible : Boolean = true
)
