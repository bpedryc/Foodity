package com.haxos.foodity.ui.moderator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.R
import com.haxos.foodity.databinding.FragmentModeratorBinding
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import com.haxos.foodity.ui.profile.ProfileFragment
import com.haxos.foodity.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ModeratorFragment : Fragment() {

    private lateinit var binding: FragmentModeratorBinding
    @Inject lateinit var moderatorViewModel: ModeratorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentModeratorBinding.inflate(inflater, container, false)

        binding.toolbarModerator.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.action_moderator_logout) {
                val activity = requireActivity()
                moderatorViewModel.logout(activity)
                val intent = Intent(context, AuthenticationActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity.finish()
                true
            } else {
                false
            }
        }

        moderatorViewModel.userPageRedirectRequest.observe (viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { profileId ->
                val userFragment = ProfileFragment.newInstance(profileId)
                replace(userFragment)
            }
        }

        return binding.also {
            it.viewModel = moderatorViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}