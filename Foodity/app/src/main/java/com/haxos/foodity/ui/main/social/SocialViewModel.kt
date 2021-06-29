package com.haxos.foodity.ui.main.social

import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haxos.foodity.data.ICurrentUserInfo
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.retrofit.IProfileService
import com.haxos.foodity.ui.main.social.logs.ILogTemplate
import com.haxos.foodity.ui.main.social.logs.UserLogService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SocialViewModel @Inject constructor(
    private val currentUserInfo: ICurrentUserInfo,
    private val profileService: IProfileService,
    private val userLogService: UserLogService
): ViewModel() {

    private var _profileLiveData = MutableLiveData<List<Profile>>()
    var profileLiveData: LiveData<List<Profile>> = _profileLiveData

    private var _logsLiveData = MutableLiveData<List<ILogTemplate>>()
    var logsLiveData: LiveData<List<ILogTemplate>> = _logsLiveData

    private var cachedProfiles = ArrayList<Profile>()

    val searchListener = SearchListener()

    init {
        profileService.getAll().enqueue(object : Callback<List<Profile>> {
            override fun onResponse(call: Call<List<Profile>>, response: Response<List<Profile>>) {
                val responseBody: List<Profile>? = response.body()
                if (responseBody != null) {
                    cachedProfiles.addAll(responseBody)
                }
            }
            override fun onFailure(call: Call<List<Profile>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        viewModelScope.launch {
            val currentUserProfileId: Long? = currentUserInfo.profileId
            if (currentUserProfileId != null) {
                _logsLiveData.value = userLogService.getDisplayableLogs(currentUserProfileId)
            }
        }

    }

    inner class SearchListener : SearchView.OnQueryTextListener {
        val handler: Handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String): Boolean {
            if (runnable != null) {
                handler.removeCallbacks(runnable!!)
            }

            if (newText == "") {
                _profileLiveData.value = ArrayList()
            } else {
                handler.postDelayed({
                    this@SocialViewModel._profileLiveData.value = cachedProfiles.filter {
                        it.username.contains(newText, true) or
                                it.firstName.contains(newText, true) or
                                it.lastName.contains(newText, true)
                    }
                },
                        500)
            }
            return true
        }
    }
}