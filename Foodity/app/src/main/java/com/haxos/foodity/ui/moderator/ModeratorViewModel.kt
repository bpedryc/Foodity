package com.haxos.foodity.ui.moderator

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.data.UserSession
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.User
import com.haxos.foodity.data.model.IUserActionListener
import com.haxos.foodity.data.model.UserViewModel
import com.haxos.foodity.retrofit.IProfileService
import com.haxos.foodity.retrofit.IUserService
import com.haxos.foodity.ui.main.notes.content.RecyclerItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class ModeratorViewModel @Inject constructor(
        val userSession: UserSession,
        private val userService: IUserService,
        private val profileService: IProfileService
) : ViewModel() {

    private val _usersLiveData = MutableLiveData<List<RecyclerItem>>()
    val usersLiveData : LiveData<List<RecyclerItem>> = _usersLiveData

    init {
        viewModelScope.launch {
            val usersResponse = userService.getUsers()
            val profilesResponse = profileService.getAll()

            if (!usersResponse.isSuccessful || !profilesResponse.isSuccessful) {
                _usersLiveData.value = listOf(createErrorRecyclerItem(
                        usersResponse.errorBody().toString()))
                return@launch
            }

            val users : List<User> = usersResponse.body() ?: return@launch
            val profiles : List<Profile> = profilesResponse.body() ?: return@launch
            users.forEach { user ->
                user.profile = profiles.firstOrNull { profile -> profile.username == user.username }
            }

            val usersWithProfiles = users.filter { it.profile != null }
            val userViewModels = usersWithProfiles.map { user ->
                    UserViewModel(user, user.profile!!, UserActionListener()) }
            _usersLiveData.value = userViewModels.map {it.toRecyclerItem()}
        }
    }

    private fun createErrorRecyclerItem(errorMessage: String) : RecyclerItem {
        val errorUser = User("", "ERROR", errorMessage,
                password = "", null, emptyList())
        val errorViewModel = UserViewModel(errorUser,
                Profile(0, "", "", ""), UserActionListener())
        return errorViewModel.toRecyclerItem()
    }

    private inner class UserActionListener : IUserActionListener {
        override fun onEdit(profile: Profile) {
            val userViewModels = usersLiveData.value
                    ?.map { it.data }
                    ?.filterIsInstance<UserViewModel>()

            val bannedProfile = userViewModels?.first { it.profile.id == profile.id }?.profile ?: return
            viewModelScope.launch {
                profileService.toggleBan(bannedProfile.id)
                _usersLiveData.value = _usersLiveData.value
            }
        }
    }

    fun logout(accountManagerActivity: Activity) {
        userSession.logout(accountManagerActivity)
    }

    private fun UserViewModel.toRecyclerItem(): RecyclerItem {
        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = R.layout.recyclerview_user
        )
    }
}