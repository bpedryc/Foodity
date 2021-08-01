package com.haxos.foodity.ui.main.tools.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentTimerBinding
import com.haxos.foodity.utils.enableBackButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimerBinding.inflate(inflater)

        binding.toolsToolbar.enableBackButton(requireActivity())

        return binding.root
    }
}