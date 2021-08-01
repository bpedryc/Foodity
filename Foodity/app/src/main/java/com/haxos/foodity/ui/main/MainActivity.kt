package com.haxos.foodity.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haxos.foodity.R
import com.haxos.foodity.databinding.ActivityMainBinding
import com.haxos.foodity.ui.main.tools.timer.FoodityTimer
import com.haxos.foodity.ui.main.tools.timer.FoodityTimerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val timers = emptyList<FoodityTimer>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = FoodityTimerAdapter(this, timers)
        binding.listviewTimers.adapter = adapter

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.fragments[0]
        val fragmentManager = fragment.childFragmentManager

        if (fragmentManager.backStackEntryCount <= 1) {
            super.onBackPressed()
        } else {
            fragmentManager.popBackStack()
        }
    }

    fun addTimer(title: String, seconds: Long) {
        val timer = FoodityTimer(title, seconds)
        timer.start()
        timers.add(timer)
    }
}
