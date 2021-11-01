package com.haxos.foodity.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.GenericResult
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.services.IProfileService
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class ProfileEditViewModel @Inject constructor(
    private val profileService: IProfileService,
    private val currentUserInfo: ICurrentUserInfo
): ViewModel() {

    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData : LiveData<Profile> = _profileLiveData

    private val _profileSavingResult = MutableLiveData<GenericResult>()
    val profileSavingResult : LiveData<GenericResult> = _profileSavingResult

    fun fetchProfile(profileId: Long) {
        viewModelScope.launch {
            val profileResponse : Response<Profile> = profileService.getById(profileId, currentUserInfo.authorization)
            val followersResponse : Response<List<Long>> = profileService.getFollowers(profileId)
            val followingResponse : Response<List<Long>> = profileService.getFollowing(profileId)

            val loadedProfile : Profile = profileResponse.body() ?: return@launch
            val followerIds : List<Long> = followersResponse.body() ?: return@launch
            val followingIds : List<Long> = followingResponse.body() ?: return@launch

            loadedProfile.followerIds = followerIds.toMutableList()
            loadedProfile.followingIds = followingIds.toMutableList()

            _profileLiveData.value = loadedProfile
        }
    }

    fun saveProfile() = viewModelScope.launch {
        _profileLiveData.value?.let {
            val response = profileService.saveOrUpdate(it)
            val modifiedProfile = response.body() ?: return@launch
            _profileSavingResult.value = GenericResult(success = 1)
        }
    }
}
