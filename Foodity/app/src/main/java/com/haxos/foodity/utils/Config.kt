package com.haxos.foodity.utils

import android.content.Context
import java.util.*

object Config {
    fun getValue(context: Context, name: String): String? {
        val assets = context.assets
        try {
            val configProperties = assets.open("config.properties")
            val properties = Properties()
            properties.load(configProperties)
            return properties.getProperty(name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}