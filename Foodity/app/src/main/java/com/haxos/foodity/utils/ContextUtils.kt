package com.haxos.foodity.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

fun Context.unwrap() : Activity {
    var context = this

    while (context !is Activity && context is ContextWrapper) {
        context = (this as ContextWrapper).baseContext
    }
    return context as Activity
}

fun Context.scanForActivity(): AppCompatActivity? {
    return when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> baseContext.scanForActivity()
        else -> null
    }
}