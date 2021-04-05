package com.haxos.foodity.ui.settings

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.databinding.FragmentSettingsMenuBinding
import com.haxos.foodity.ui.authentication.AuthenticationActivity
import com.haxos.foodity.ui.profile.ProfileFragment
import com.haxos.foodity.ui.utils.replace
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsMenuFragment: Fragment() {

    @Inject lateinit var userSession: UserSession
    private lateinit var binding: FragmentSettingsMenuBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSettingsMenuBinding.inflate(inflater)

        binding.buttonLogout.setOnClickListener { logOut() }

        binding.buttonProfile.setOnClickListener { viewProfile() }

        return binding.root
    }

    private fun viewProfile() {
        val profileId = userSession.user?.profile?.id
        if (profileId != null) {
            val profileFragment = ProfileFragment.newInstance(profileId)
            replace(profileFragment)
        }
    }

    private fun logOut() {
        val loggedOutUser = userSession.logout()
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