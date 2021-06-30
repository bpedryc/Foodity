package com.haxos.foodity.ui.profile

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.IProfileService
import kotlinx.coroutines.launch
import retrofit2.Response
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
            val profileResponse : Response<Profile> = profileService.getById(profileId)
            val followersResponse : Response<List<Long>> = profileService.getFollowers(profileId)
            val followingResponse : Response<List<Long>> = profileService.getFollowing(profileId)

            val profile : Profile = profileResponse.body() ?: return@launch
            val followerIds : List<Long> = followersResponse.body() ?: return@launch
            val followingIds : List<Long> = followingResponse.body() ?: return@launch

            profile.followerIds = followerIds.toMutableList()
            profile.followingIds = followingIds.toMutableList()

            _profileLiveData.value = profile
        }
    }

    fun isCurrentProfile(profileId: Long): Boolean {
        return currentUserInfo.profileId == profileId
    }

    fun isFollowedByCurrentUser(profile: Profile): Boolean {
        return profile.followerIds.any { it == currentUserInfo.profileId }
    }

    inner class UnfollowClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val currentProfileId : Long = currentUserInfo.profileId ?: return
            val profile : Profile = _profileLiveData.value ?: return

            val followerIds = profile.followerIds
            followerIds.remove(currentProfileId)

            viewModelScope.launch {
                profileService.removeFollower(profile.id, currentProfileId)
                _profileLiveData.value = _profileLiveData.value
            }
        }
    }

    inner class FollowClickListener : View.OnClickListener {
        override fun onClick(view: View) {
            val currentProfileId : Long = currentUserInfo.profileId ?: return
            val profile : Profile = _profileLiveData.value ?: return

            profile.followerIds.add(currentProfileId)
            viewModelScope.launch {
                profileService.addFollower(profile.id, currentProfileId)
                _profileLiveData.value = _profileLiveData.value
            }
        }
    }
}
