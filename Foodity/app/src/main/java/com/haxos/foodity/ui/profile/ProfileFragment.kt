package com.haxos.foodity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentProfileBinding
import com.haxos.foodity.ui.main.notes.categories.CategoriesFragment
import com.haxos.foodity.utils.replace
import com.koushikdutta.ion.Ion
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        fun newInstance(profileId: Long): ProfileFragment{
            val args = Bundle()
            args.putLong("profileId", profileId)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    private var profileId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater)

        profileId = arguments?.getLong("profileId")
                ?: return binding.root

        profileViewModel.profileLiveData.observe(viewLifecycleOwner, {
            binding.apply {
                fullName.text = it.firstName + " " + it.lastName
                username.text = it.username
                description.text = it.description

                Ion.with(avatar)
                        .placeholder(R.drawable.avatar)
                        .error(R.drawable.avatar)
                        .load(it.avatarSrc)

                followersCount.text = it.followerIds.size.toString()
                followingCount.text = it.followingIds.size.toString()

                val alreadyFollowed = profileViewModel.isFollowedByCurrentUser(it)
                if (alreadyFollowed) {
                    buttonFollow.text = getString(R.string.profile_button_unfollow)
                    buttonFollow.setOnClickListener(profileViewModel.unfollowClickListener)
                } else {
                    buttonFollow.text = getString(R.string.profile_button_follow)
                    buttonFollow.setOnClickListener(profileViewModel.followClickListener)
                }
            }
        })


        binding.toolbarProfile.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonViewNotes.setOnClickListener {
            replace(CategoriesFragment.newInstance(profileId))
        }

        profileViewModel.fetchProfile(profileId)

        if (profileViewModel.isCurrentProfile(profileId)) {
            binding.root.removeView(binding.layoutActions)
        }
        if (profileViewModel.canEdit(profileId)) {
            binding.toolbarProfile.inflateMenu(R.menu.menu_profile)
            binding.toolbarProfile.setOnMenuItemClickListener(this)
            setHasOptionsMenu(true)
        }

        return binding.root
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile_edit -> {
                replace(ProfileEditFragment.newInstance(profileId))
            }
        }
        return true
    }
}