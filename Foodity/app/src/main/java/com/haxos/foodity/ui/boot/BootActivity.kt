package com.haxos.foodity.ui.boot

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.haxos.foodity.R
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.data.model.User
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import com.haxos.foodity.ui.main.MainActivity
import com.haxos.foodity.ui.moderator.ModeratorActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BootActivity : ComponentActivity()
{
    @Inject lateinit var userSession: UserSession
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

            lifecycleScope.launch {
                val username : String = userAccount!!.name
                val password : String = accountManager.getPassword(userAccount)
                val loggedInUser : User? = userSession.login(username, password)

                if (loggedInUser == null) {
                    AlertDialog.Builder(this@BootActivity)
                            .setTitle(getString(R.string.dialog_title_warning))
                            .setMessage(getString(R.string.dialog_message_missinginternet))
                            .setPositiveButton(android.R.string.yes) { _, _ ->
                                finish()
                            }
                            .show()
                    return@launch
                }

                if (loggedInUser.roles.any { it.contains("moderator")} ) {
                    startActivity(Intent(this@BootActivity, ModeratorActivity::class.java))
                    return@launch
                }
                startActivity(Intent(this@BootActivity, MainActivity::class.java))

            }
        }
    }
}