package com.haxos.foodity.ui.moderator

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.haxos.foodity.data.UserSession
import javax.inject.Inject

class ModeratorViewModel @Inject constructor(
    val userSession: UserSession
) : ViewModel() {

    fun logout(accountManagerActivity: Activity) {
        userSession.logout(accountManagerActivity)
    }

}