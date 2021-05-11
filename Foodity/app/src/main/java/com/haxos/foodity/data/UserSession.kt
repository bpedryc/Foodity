package com.haxos.foodity.data

import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.Token
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IAuthService
import com.haxos.foodity.retrofit.IProfileService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(
        private val authService: IAuthService,
        private val profileService: IProfileService
) : ICurrentUserInfo {
    override var user: User? = null
        private set

    override val isLoggedIn: Boolean
        get() = user != null

    override val profileId: Long?
        get() = user?.profile?.id

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