package com.haxos.foodity.ui.main.tools.unitconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentUnitconverterBinding
import com.haxos.foodity.utils.enableBackButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UnitConverterFragment : Fragment() {

    private lateinit var binding : FragmentUnitconverterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnitconverterBinding.inflate(inflater)

        binding.toolsToolbar.enableBackButton(requireActivity())

        binding.apply {
            spinnerUnittype.adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.unittypes,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerUnittype.adapter = adapter
            }
        }

        return binding.root
    }
}