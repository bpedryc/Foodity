package com.haxos.foodity.ui.settings

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.haxos.foodity.R
import com.haxos.foodity.data.LoginRepository
import com.haxos.foodity.databinding.FragmentSettingsMenuBinding
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import com.haxos.foodity.ui.main.notes.NotesGridFragment
import com.haxos.foodity.ui.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsMenuFragment: Fragment() {

    @Inject lateinit var loginRepository: LoginRepository
    private lateinit var binding: FragmentSettingsMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsMenuBinding.inflate(inflater)

        binding.buttonLogout.setOnClickListener { logOut() }

        binding.buttonProfile.setOnClickListener { viewProfile() }

        return binding.root
    }

    private fun viewProfile() {
        val profileId = loginRepository.profileId
        if (profileId != null) {
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction()
            fragmentManager.commit {
                val profileFragment = ProfileFragment.newInstance(profileId)
                replace(R.id.fragment_settings, profileFragment)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
            //TODO: logOut
        }
    }

    private fun logOut() {
        val loggedOutUser = loginRepository.logout()
        val accountManager = AccountManager.get(activity)

        if (loggedOutUser != null) {
            Account(loggedOutUser.username, "com.haxos.foodity").also { account ->
                accountManager.removeAccount(account, activity, null, null)
            }
        }

        val intent = Intent(activity, AuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        activity?.finish()
    }

}