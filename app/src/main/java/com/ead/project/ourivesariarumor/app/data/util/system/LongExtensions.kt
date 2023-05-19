package com.ead.project.ourivesariarumor.app.data.util.system

fun Long.toMinutes() : Int {
    return (this / 60000).toInt()
}