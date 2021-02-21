package com.haxos.foodity

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationService : Service() {

    @Inject lateinit var authService: AuthService

    override fun onBind(intent: Intent): IBinder {
        val authenticator = AccountAuthenticator(this, authService)
        return authenticator.iBinder
    }
}