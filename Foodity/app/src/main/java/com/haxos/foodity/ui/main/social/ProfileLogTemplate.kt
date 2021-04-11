package com.haxos.foodity.ui.main.social

import android.content.Context
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Profile
import com.haxos.foodity.data.model.ProfileLog
import com.haxos.foodity.data.model.ProfileLog.Type.*

class ProfileLogTemplate(private val profileLog: ProfileLog) : ILogTemplate {
    override val infoTemplate: Int
        get() = when (profileLog.type) {
            FOLLOWED -> R.string.profilelog_followed
            UNFOLLOWED -> R.string.profilelog_unfollowed
        }

    override fun getInContext(context: Context): DisplayableLog {
        val user: Profile = profileLog.user
        val targetUser: Profile = profileLog.target

        val logTimestamp = profileLog.timestamp.toString()

        val logInfo = context.getString(infoTemplate, user.username, targetUser.username)
        return DisplayableLog(logTimestamp, logInfo)
    }
}
