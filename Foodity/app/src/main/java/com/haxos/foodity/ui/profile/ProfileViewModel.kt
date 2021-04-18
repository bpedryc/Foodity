package com.haxos.foodity.ui.profile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.IProfileService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val currentUserInfo: ICurrentUserInfo,
    val profileService: IProfileService
): ViewModel() {
    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData: LiveData<Profile> = _profileLiveData

    val unfollowClickListener = UnfollowClickListener()
    val followClickListener = FollowClickListener()

    fun fetchProfile(profileId: Long) {
        viewModelScope.launch {
            val profileResponse = profileService.getById(profileId)
            _profileLiveData.value = profileResponse.body()
        }
    }

    inner class UnfollowClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val profile : Profile = _profileLiveData.value ?: return
            val followerToDelete: Profile = profile.followers.find {it.id == currentUserInfo.profileId} ?: return
            profile.followers.remove(followerToDelete)
            runBlocking {
                val followerRemoval = async { profileService.removeFollower(profile.id, followerToDelete.id) }
                followerRemoval.await()
                _profileLiveData.postValue(profile)
            }
        }
    }

    inner class FollowClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val profile : Profile = _profileLiveData.value ?: return
            val followerToAdd = currentUserInfo.user?.profile ?: return
            profile.followers.add(followerToAdd)
            runBlocking {
                val followerSaving = async { profileService.addFollower(profile.id, followerToAdd.id) }
                followerSaving.await()
                _profileLiveData.postValue(profile)
            }
        }
    }
}
