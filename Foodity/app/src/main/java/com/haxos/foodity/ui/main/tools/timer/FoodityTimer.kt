package com.haxos.foodity.ui.main.tools.timer

import android.os.Handler
import android.os.Looper

class FoodityTimer (
        val title: String,
        var seconds: Long,
        var onTimerFinished: () -> Unit = {},
        var handler: Handler = Handler(Looper.getMainLooper())
) {
    var startTimeMillis = 0L

    var runnable = object : Runnable {
        override fun run() {
            if (secondsLeft > 0) {
                handler.postDelayed(this, 1000)
                return
            }
            onTimerFinished.invoke()
        }
    }

    val secondsLeft : Long
    get() {
        val secondsLeft = seconds - ((System.currentTimeMillis() - startTimeMillis) / 1000)
        if (secondsLeft < 0) {
            return 0L
        }
        return secondsLeft
    }

    fun start() {
        startTimeMillis = System.currentTimeMillis()
        runnable.run()
    }
}