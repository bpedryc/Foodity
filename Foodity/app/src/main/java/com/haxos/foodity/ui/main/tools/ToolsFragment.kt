package com.haxos.foodity.ui.main.tools

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentToolsBinding
import com.haxos.foodity.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ToolsFragment : Fragment() {

    @Inject lateinit var toolsViewModel: ToolsViewModel
    private lateinit var binding: FragmentToolsBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentToolsBinding.inflate(inflater)

        val toolbar: Toolbar = binding.toolbarFragmentTools
        toolbar.title = getString(R.string.tools_title)
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }

        val textView: TextView = binding.textNotifications
        toolsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return binding.root
    }
}
