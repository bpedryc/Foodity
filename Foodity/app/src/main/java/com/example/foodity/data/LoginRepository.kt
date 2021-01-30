package com.example.foodity.data

import androidx.lifecycle.MutableLiveData
import com.example.foodity.LoginCallback
import com.example.foodity.R
import com.example.foodity.UserService
import com.example.foodity.data.model.User
import com.example.foodity.ui.login.LoggedInUserView
import com.example.foodity.ui.login.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository @Inject constructor(
        private val userService : UserService
) {

    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
//        dataSource.logout()
    }

    fun login(username: String, password: String, loginCallback: LoginCallback) {
        userService.getUsersByUsername(username)
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.body()?.password == password) {
                            loginCallback.onSuccess(response.body()!!)
                        } else {
                            loginCallback.onError(R.string.login_failed)
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        loginCallback.onError(R.string.login_failed)
                    }
                })
        // handle login
//        val result = dataSource.login(username, password)

        /*if (result is Result.Success) {
            setLoggedInUser(result.data)
        }*/
    }

    private fun setLoggedInUser(user: User) {
        this.user = user
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
