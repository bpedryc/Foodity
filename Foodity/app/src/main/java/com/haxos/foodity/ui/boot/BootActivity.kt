package com.haxos.foodity.ui.boot

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.haxos.foodity.MainActivity
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootActivity : ComponentActivity()
{
    @Inject lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val accountManager = AccountManager.get(this)
        val options = Bundle()

        val accounts = accountManager.getAccountsByType("com.haxos.foodity")
        if (accounts.isEmpty()){
            startActivity(Intent(this, AuthenticationActivity::class.java))
            return
        }

        accountManager.getAuthToken(
            accounts[0],
            "USER_ACCESS",
            options,
            this,
            OnTokenAcquired(),
            null
        )
    }

    private inner class OnTokenAcquired : AccountManagerCallback<Bundle> {
        override fun run(result: AccountManagerFuture<Bundle>) {
            val bundle: Bundle = result.result
            val token: String? = bundle.getString(AccountManager.KEY_AUTHTOKEN)
            if (token == null){
                startActivity(Intent(this@BootActivity, AuthenticationActivity::class.java))
            }
            startActivity(Intent(this@BootActivity, MainActivity::class.java))
        }
    }
}