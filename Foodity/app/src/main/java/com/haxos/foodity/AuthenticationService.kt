package com.haxos.foodity

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AuthenticationService : Service() {

    override fun onBind(intent: Intent): IBinder {
        val authenticator = AccountAuthenticator(this)
        return authenticator.iBinder
    }
}