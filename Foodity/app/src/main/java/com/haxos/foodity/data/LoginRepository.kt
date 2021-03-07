package com.haxos.foodity.data

import com.haxos.foodity.*
import com.haxos.foodity.data.model.Token
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.AuthService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

@Singleton
class LoginRepository @Inject constructor(
        private val authService: AuthService
) {
    var user: User? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    fun logout(): User? {
        val loggedOutUser = user?.copy()
        user = null
        return loggedOutUser
    }

    fun login(username: String, password: String, loginCallback: LoginCallback?) {
        authService.getToken(username, password)
                .enqueue(object : Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if (response.body() != null){
                            val user = User(0, username, "TEST", "TEST")
                            loginCallback?.onSuccess(user)
                            setLoggedInUser(user)
                        } else {
                            loginCallback?.onError(R.string.login_failed)
                        }
                    }
                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        loginCallback?.onError(R.string.login_failed)
                    }
                })
    }

    private fun setLoggedInUser(user: User) {
        this.user = user
    }
}
