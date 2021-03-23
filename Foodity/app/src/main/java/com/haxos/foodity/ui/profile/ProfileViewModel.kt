package com.haxos.foodity.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.IProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val profileService: IProfileService
){
    private val _profileLiveData = MutableLiveData<Profile>()
    val profileLiveData: LiveData<Profile> = _profileLiveData

    fun fetchProfile(profileId: Long) {
        profileService.getById(profileId).enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                _profileLiveData.value = response.body()
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}
