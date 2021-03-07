package com.haxos.foodity.ui.main.social

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.data.model.User
import com.haxos.foodity.databinding.FragmentSocialBinding
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SocialFragment : Fragment() {

    @Inject lateinit var socialViewModel: SocialViewModel
    private lateinit var binding: FragmentSocialBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentSocialBinding.inflate(inflater)

        val usersRecyclerView: RecyclerView = binding.recyclerViewUsers
        usersRecyclerView.layoutManager = LinearLayoutManager(context)

        val profilesAdapter = SearchResultAdapter()
        usersRecyclerView.adapter = profilesAdapter
        socialViewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                profilesAdapter.addProfiles(it)
                profilesAdapter.notifyDataSetChanged()
            }
        })

        val toolbar: Toolbar = binding.toolbarActivityMain
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }

        val textView: TextView = binding.textDashboard
        socialViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return binding.root
    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        *//*menu?.add(Menu.NONE, R.id.my_toolbar, 10, R.string.app_name)
        return super.onCreateOptionsMenu(menu)*//*
    }*/
}
