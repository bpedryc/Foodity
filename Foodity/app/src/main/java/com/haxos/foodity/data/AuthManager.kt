package com.haxos.foodity.data

import com.haxos.foodity.*
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.Token
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IAuthService
import com.haxos.foodity.retrofit.IProfileService
import kotlinx.coroutines.*
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
class AuthManager @Inject constructor(
        private val authService: IAuthService,
        private val profileService: IProfileService
) {
    var user: User? = null
        private set

    var profileId: Long? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    fun logout(): User? {
        val loggedOutUser = user?.copy()
        user = null
        return loggedOutUser
    }

    suspend fun loginAsync(username: String, password: String) : Long? = coroutineScope {
        val tokenResponse = authService.getToken(username, password)
        if (tokenResponse.isSuccessful && tokenResponse.body() != null) {
            val profileResponse = profileService.getByUsername(username)
            profileId = profileResponse.body()?.id
            return@coroutineScope profileId
        }
        return@coroutineScope null
    }

        /*CoroutineScope(Dispatchers.IO).launch {
            val tokenResponse = authService.getToken(username, password)
            if (tokenResponse.isSuccessful) {
                tokenResponse.body()
            }
        }*/


    /*fun login(username: String, password: String, loginCallback: LoginCallback?) {
        authService.getTokenSync(username, password)
                .enqueue(object : Callback<Token> {
                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        if (response.body() != null){
                            val user = User(0, username, "TEST", "TEST")
                            setLoggedInUser(user)
                            loginCallback?.onSuccess(user)
                        } else {
                            loginCallback?.onError(R.string.login_failed)
                        }
                    }
                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        loginCallback?.onError(R.string.login_failed)
                    }
                    fun setLoggedInUser(user: User) {
                        this@AuthManager.user = user

                        val profileCall : Call<Profile> = profileService.getByUsername(user.username)
                        val profileResponse : Response<Profile> = profileCall.execute()
                        val profile : Profile? = profileResponse.body()
                        if (profile != null) {
                            profileId = profile.id
                        }
                    }
                })
    }*/
}
