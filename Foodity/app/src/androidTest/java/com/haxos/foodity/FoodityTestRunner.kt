package com.haxos.foodity

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy


class FoodityTestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().permitAll().build())
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}