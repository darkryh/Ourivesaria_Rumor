package com.ead.project.ourivesariarumor.app.data.util.system


fun String.hasOnlyNumbers() : Boolean {
    for (char in this) {
        if (!char.isDigit()) return false
    }

    return true
}