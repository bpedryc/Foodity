package com.haxos.foodity.data

import android.accounts.Account
import android.accounts.AccountManager
import android.app.Activity
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.Token
import com.haxos.foodity.data.model.User
import com.haxos.foodity.retrofit.IAuthService
import com.haxos.foodity.retrofit.IProfileService
import com.haxos.foodity.retrofit.IUserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(
        private val authService: IAuthService,
        private val profileService: IProfileService,
        private val userService: IUserService
) : ICurrentUserInfo {
    override var user: User? = null
        private set

    override val isLoggedIn: Boolean
        get() = user != null

    override val userRoles: List<String>
        get() = user?.roles ?: emptyList()

    override val profileId: Long?
        get() = user?.profile?.id

    fun logout(accountManagerActivity: Activity): User? {
        var loggedOutUser : User? = null
        user?.let {
            loggedOutUser = User(it.id, it.username, it.email, it.password, it.profile, it.roles)
            val accountManager = AccountManager.get(accountManagerActivity)
            Account(it.username, "com.haxos.foodity").also { account ->
                accountManager.removeAccount(
                        account, accountManagerActivity, null, null)
            }
        }

        user = null
        return loggedOutUser
    }

    suspend fun login(username: String, password: String) : User? {
        val tokenResponse: Token = authService.getToken(username, password).body() ?: return null
        val userProfile: Profile? = profileService.getByUsername(username).body()
        val userRoles: List<String> = userService.getUserRoles(username).body() ?: emptyList()

        user = User("ABCD", username, "", "", userProfile, userRoles)
        return user
    }
}