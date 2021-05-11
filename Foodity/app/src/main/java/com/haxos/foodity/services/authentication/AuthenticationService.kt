package com.haxos.foodity.services.authentication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.haxos.foodity.retrofit.IAuthService
import com.haxos.foodity.services.authentication.AccountAuthenticator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthenticationService : Service() {

    @Inject lateinit var authService: IAuthService

    override fun onBind(intent: Intent): IBinder {
        val authenticator = AccountAuthenticator(this, authService)
        return authenticator.iBinder
    }
}