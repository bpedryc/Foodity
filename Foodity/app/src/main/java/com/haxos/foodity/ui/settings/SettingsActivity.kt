package com.haxos.foodity.ui.settings

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.haxos.foodity.R
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IProfileService
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import com.haxos.foodity.ui.main.notes.CategoriesGridFragment
import com.haxos.foodity.ui.profile.ProfileFragment
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

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SettingsMenuFragment>(R.id.fragment_settings)
        }
    }
}