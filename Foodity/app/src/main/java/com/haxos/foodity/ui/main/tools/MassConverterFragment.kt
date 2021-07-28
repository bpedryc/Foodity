package com.haxos.foodity.ui.main.tools

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentMassconverterBinding
import com.haxos.foodity.utils.enableBackButton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MassConverterFragment : Fragment()
{
    private lateinit var binding: FragmentMassconverterBinding
    @Inject lateinit var converterViewModel: MassConverterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMassconverterBinding.inflate(inflater)

        binding.apply {
            toolsToolbar.enableBackButton(requireActivity())
        }

        return binding.root
    }
}