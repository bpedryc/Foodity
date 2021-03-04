package com.haxos.foodity.ui.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.R
import com.haxos.foodity.retrofit.UserService
import com.haxos.foodity.data.model.KeycloakUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
        private val userService: UserService
) {
    private var _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(username: String, password: String) {
        userService.createUser(KeycloakUser(username, password)).enqueue(
            object : Callback<KeycloakUser> {
                override fun onResponse(call: Call<KeycloakUser>, response: Response<KeycloakUser>) {
                    if (response.body() != null) {
                        _registerResult.value = RegisterResult(success = R.string.register_success)
                    } else {
                        _registerResult.value = RegisterResult(error = R.string.register_failed)
                    }
                }
                override fun onFailure(call: Call<KeycloakUser>, t: Throwable) {
                    _registerResult.value = RegisterResult(error = R.string.register_failed)
                }

            }
        )
    }
}
