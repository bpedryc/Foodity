package com.haxos.foodity.ui.main.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.ProfileService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SocialViewModel @Inject constructor(
         private val profileRepository: ProfileService
): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Social Fragment"
    }
    val text: LiveData<String> = _text

    private var _profileLiveData = MutableLiveData<List<Profile>>()
    var profileLiveData : LiveData<List<Profile>> = _profileLiveData

    init {
        profileRepository.getAll().enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                _profileLiveData.value = response.body()
            }
            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                print("ERROR")
            }
        })
    }
}