package com.haxos.foodity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentProfileBinding
import com.haxos.foodity.ui.main.notes.categories.CategoriesFragment
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater)

        val profileId : Long = arguments?.getLong("profileId")
                ?: return binding.root

        profileViewModel.profileLiveData.observe(viewLifecycleOwner, {
            binding.fullName.text = it.firstName + " " + it.lastName
            binding.username.text = it.username

            binding.followersCount.text = it.followerIds.size.toString()
            binding.followingCount.text = it.followingIds.size.toString()

            val alreadyFollowed = profileViewModel.isFollowedByCurrentUser(it)
            if (alreadyFollowed) {
                binding.buttonFollow.text = "Unfollow"
                binding.buttonFollow.setOnClickListener(profileViewModel.unfollowClickListener)
            } else {
                binding.buttonFollow.text = "Follow"
                binding.buttonFollow.setOnClickListener(profileViewModel.followClickListener)
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

        return binding.root
    }
}