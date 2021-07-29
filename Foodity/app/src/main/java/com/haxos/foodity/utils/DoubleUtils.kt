package com.haxos.foodity.utils

import kotlin.math.round

fun Double.toPrettyString() : String {
    val trimmedDouble = this.toLong().toDouble()
    if (this == trimmedDouble) {
        return this.toLong().toString()
    }
    return this.round(3).toString()
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}