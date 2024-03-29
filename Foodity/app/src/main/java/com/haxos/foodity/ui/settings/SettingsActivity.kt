package com.haxos.foodity.ui.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.haxos.foodity.R
import com.haxos.foodity.data.UserSession
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    @Inject lateinit var userSession: UserSession

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<SettingsMenuFragment>(R.id.fragment_settings)
        }
    }
}