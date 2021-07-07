package com.haxos.foodity.ui.moderator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.haxos.foodity.databinding.ActivityModeratorBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ModeratorActivity : AppCompatActivity() {

    private lateinit var binding : ActivityModeratorBinding
    @Inject lateinit var viewModel : ModeratorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityModeratorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}