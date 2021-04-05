package com.haxos.foodity.ui.main.social

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.databinding.FragmentSocialBinding
import com.haxos.foodity.ui.main.SearchResultAdapter
import com.haxos.foodity.ui.profile.ProfileFragment
import com.haxos.foodity.ui.settings.SettingsActivity
import com.haxos.foodity.ui.utils.replace
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

        val toolbar: Toolbar = binding.toolbarActivityMain
        toolbar.setNavigationOnClickListener {
            startActivity(Intent(activity, SettingsActivity::class.java))
        }

        val searchView = binding.mySearchView
        searchView.setOnQueryTextListener(socialViewModel.searchListener)

        val profilesRecyclerView: RecyclerView = binding.recyclerViewUsers
        profilesRecyclerView.layoutManager = LinearLayoutManager(context)

        val searchAdapter = object : SearchResultAdapter(clickListener = ProfileSearchClickListener()) {
            override fun getTextToDisplay(objectToDisplay: Any): String {
                return (objectToDisplay as Profile).username
            }
        }
        profilesRecyclerView.adapter = searchAdapter
        socialViewModel.profileLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                searchAdapter.setItems(it)
            }
        })

        val textView: TextView = binding.textDashboard
        socialViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return binding.root
    }

    inner class ProfileSearchClickListener : SearchResultAdapter.IItemClickListener {
        override fun onItemClick(item: Any) {
            val profile: Profile = item as Profile
            replace(ProfileFragment.newInstance(profile.id))
        }
    }
}

