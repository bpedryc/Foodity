package com.haxos.foodity.ui.settings

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.haxos.foodity.R
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    @Inject lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_activity_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun logOut(view: View) {
        val loggedOutUser = loginRepository.logout()
        val accountManager = AccountManager.get(this)

        Account(loggedOutUser?.username, "com.haxos.foodity").also { account ->
            accountManager.removeAccount(account, this, null, null)
        }

        val intent = Intent(this, AuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}