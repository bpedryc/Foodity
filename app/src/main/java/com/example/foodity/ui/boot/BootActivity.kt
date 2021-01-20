package com.example.foodity.ui.boot

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.foodity.MainActivity
import com.example.foodity.data.LoginRepository
import com.example.foodity.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BootActivity : ComponentActivity()
{
    @Inject lateinit var loginRepository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = "" //get from cache
        val password = "" //get from cache
        //loginRepository.login(username, password)

        if (loginRepository.isLoggedIn){
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}