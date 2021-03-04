package com.haxos.foodity.ui.boot

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.haxos.foodity.data.LoginCallback
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.data.model.User
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.haxos.foodity.ui.main.MainActivity

@AndroidEntryPoint
class BootActivity : ComponentActivity()
{
    @Inject lateinit var loginRepository: LoginRepository
    private lateinit var accountManager: AccountManager

    private val userAccount : Account?
        get() {
            val accounts = accountManager.getAccountsByType("com.haxos.foodity")
            if (accounts.isNotEmpty()) {
                return accounts[0]
            }
            return null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountManager = AccountManager.get(this)

        if (userAccount == null){
            startActivity(Intent(this, AuthenticationActivity::class.java))
            return
        }

        accountManager.getAuthToken(
            userAccount,
            "USER_ACCESS",
            Bundle(),
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
                return
            }
            loginRepository.login(userAccount!!.name, accountManager.getPassword(userAccount),
            object : LoginCallback {
                override fun onSuccess(user: User) {
                    startActivity(Intent(this@BootActivity, MainActivity::class.java))
                }
                override fun onError(error: Int) {
                    val dialog = AlertDialog.Builder(this@BootActivity)
                            .setTitle("Warning")
                            .setMessage("This app requires an active internet connection")
                            .setPositiveButton(android.R.string.yes) { _, _ ->
                                finish()
                            }
                            .show()
                }
            })

        }
    }
}