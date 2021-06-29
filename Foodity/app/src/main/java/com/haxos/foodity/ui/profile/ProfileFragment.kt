package com.haxos.foodity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.databinding.FragmentProfileBinding
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

    @Inject lateinit var currentUserInfo : ICurrentUserInfo
    @Inject lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProfileBinding.inflate(inflater)

        profileViewModel.profileLiveData.observe(viewLifecycleOwner, {
            binding.fullName.text = it.firstName + " " + it.lastName
            binding.username.text = it.username

            binding.followersCount.text = it.followerIds.size.toString()
            binding.followingCount.text = it.followingIds.size.toString()

            val alreadyFollowed = it.followerIds.any { follower ->
                follower == currentUserInfo.user?.profile?.id }

            if (alreadyFollowed) {
                binding.buttonFollow.text = "Unfollow"
                binding.buttonFollow.setOnClickListener(profileViewModel.unfollowClickListener)
            } else {
                binding.buttonFollow.text = "Follow"
                binding.buttonFollow.setOnClickListener(profileViewModel.followClickListener)
            }
        })

        val profileId = arguments?.getLong("profileId")
        if (profileId != null) {
            profileViewModel.fetchProfile(profileId)

            if (profileId == currentUserInfo.user?.profile?.id) {
                binding.root.removeView(binding.layoutActions)
            }
        }
        return binding.root
    }
}