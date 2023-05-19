package com.ead.project.ourivesariarumor.app.data.util.system

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(text : String, duration : Int = Toast.LENGTH_LONG) {
    Toast.makeText(this,text,duration).show()
}

fun Context.launchActivity(cls: Class<*>?=null, intent: Intent?=null) {
    startActivity(intent ?: Intent(this,cls))
}

fun Context.launchActivityAndFinish(cls: Class<*>?=null, intent: Intent?=null) {
    launchActivity(cls = cls, intent = intent)
    (this as Activity).finish()
}