package com.haxos.foodity.ui.main.tools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentToolsBinding
import com.haxos.foodity.ui.main.tools.unitconverter.UnitConverterFragment
import com.haxos.foodity.ui.main.tools.weightconverter.WeightConverterFragment
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class
ToolsFragment : Fragment() {

    @Inject lateinit var toolsViewModel: ToolsViewModel
    private lateinit var binding: FragmentToolsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentToolsBinding.inflate(inflater)

        val toolbar: Toolbar = binding.toolbarTools
        toolbar.title = getString(R.string.tools_title)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }

        binding.toolsButtonMassconverter.setOnClickListener {
            replace(WeightConverterFragment())
        }
        binding.toolsButtonUnitconverter.setOnClickListener {
            replace(UnitConverterFragment())
        }

        return binding.root
    }
}
