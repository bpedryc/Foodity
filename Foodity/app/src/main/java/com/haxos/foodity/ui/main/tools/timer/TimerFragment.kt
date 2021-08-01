package com.haxos.foodity.ui.main.tools.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentTimerBinding
import com.haxos.foodity.ui.main.MainActivity
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

        binding.apply {
            toolsToolbar.enableBackButton(requireActivity())

            pickerHours.maxValue = 99
            pickerHours.minValue = 0

            pickerMinutes.maxValue = 59
            pickerMinutes.minValue = 0

            pickerSeconds.maxValue = 59
            pickerSeconds.minValue = 0

            timerButtonSet.setOnClickListener {
                val activity = requireActivity() as MainActivity
                val timerTitle = editTitle.text.toString()
                val timerSeconds : Long = pickerHours.value * 3600L + pickerMinutes.value * 60L +
                        pickerSeconds.value
                activity.addTimer(timerTitle, timerSeconds)
            }
        }

        return binding.root
    }
}