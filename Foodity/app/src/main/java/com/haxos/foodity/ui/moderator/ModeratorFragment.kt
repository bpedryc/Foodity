package com.haxos.foodity.ui.moderator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.databinding.FragmentModeratorBinding
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ModeratorFragment : Fragment() {

    private lateinit var binding: FragmentModeratorBinding
    @Inject lateinit var viewModel: ModeratorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentModeratorBinding.inflate(inflater)

        val toolbar = binding.toolbarModerator
        toolbar.setNavigationOnClickListener {
            val activity = requireActivity()
            viewModel.logout(activity)
            val intent = Intent(context, AuthenticationActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            activity.finish()
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}