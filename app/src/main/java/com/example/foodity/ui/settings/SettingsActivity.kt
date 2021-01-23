package com.example.foodity.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.foodity.R
import com.example.foodity.ui.login.LoginActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        setSupportActionBar(findViewById(R.id.settings_activity_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val logoutButton: Button = findViewById(R.id.button_logout)
        logoutButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}