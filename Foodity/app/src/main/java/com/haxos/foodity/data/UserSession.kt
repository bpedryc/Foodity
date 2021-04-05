package com.haxos.foodity.data

import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.Token
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IAuthService
import com.haxos.foodity.retrofit.IProfileService
import javax.inject.Inject
import javax.inject.Singleton

interface ICurrentUserInfo {
    val user: User?
    val isLoggedIn: Boolean
}

@Singleton
class UserSession @Inject constructor(
        private val authService: IAuthService,
        private val profileService: IProfileService
) : ICurrentUserInfo {
    override var user: User? = null
        private set

    override val isLoggedIn: Boolean
        get() = user != null

    fun logout(): User? {
        val loggedOutUser = user?.copy()
        user = null
        return loggedOutUser
    }

    suspend fun login(username: String, password: String) {
        val tokenResponse: Token = authService.getToken(username, password).body() ?: return
        val userProfile: Profile = profileService.getByUsername(username).body() ?: return

        user = User(0, username, "", "", userProfile)
    }
}

/*var profileId: Long? = null
    private set*/

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

