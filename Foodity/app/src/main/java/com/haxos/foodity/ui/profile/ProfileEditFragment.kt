package com.haxos.foodity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentProfileEditBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileEditFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    @Inject lateinit var profileViewModel: ProfileEditViewModel

    companion object {
        fun newInstance(profileId: Long): ProfileEditFragment {
            val args = Bundle()
            args.putLong("profileId", profileId)
            val fragment = ProfileEditFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var binding : FragmentProfileEditBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileEditBinding.inflate(inflater, container, false)

        val profileId : Long = arguments?.getLong("profileId") ?: return binding.root
        profileViewModel.fetchProfile(profileId)

        profileViewModel.profileSavingResult.observe(viewLifecycleOwner) {
            if (it.success != null) {
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(context,
                        "Error trying to save profile changes", Toast.LENGTH_LONG).show()
            }
        }

        binding.toolbarProfile.apply {
            setNavigationOnClickListener { requireActivity().onBackPressed() }
            setOnMenuItemClickListener(this@ProfileEditFragment)
        }

        return binding.also {
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = profileViewModel
        }.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile_confirm -> {
                profileViewModel.saveProfile()
            }
        }
        return true
    }
}