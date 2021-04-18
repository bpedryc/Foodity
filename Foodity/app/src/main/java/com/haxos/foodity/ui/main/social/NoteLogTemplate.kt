package com.haxos.foodity.ui.main.social

import android.content.Context
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteLog
import com.haxos.foodity.data.model.NoteLog.Type.*
import com.haxos.foodity.data.model.Profile
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.absoluteValue

class NoteLogTemplate(private val noteLog: NoteLog) : ILogTemplate {
    override val infoTemplate: Int
        get() = when (noteLog.type) {
                CREATE -> R.string.notelog_create
                EDIT -> R.string.notelog_edit
                DELETE -> R.string.notelog_delete
                LIKE -> R.string.notelog_like
                COMMENT -> R.string.notelog_commentself
            }

    private fun targetingSelf() : Boolean {
        if (noteLog.target.id == noteLog.profile.id) {
            return true
        }
        return false
    }

    override fun getInContext(context: Context) : DisplayableLog {
        val logTimestamp = getTimestampString()
        val logInfo = getLogInfoString(context)

        return DisplayableLog(logTimestamp, logInfo)
    }

    private fun getTimestampString() : String {
        val now = LocalDateTime.now()
        val timestamp = noteLog.timestamp

        val yesterday = now.minusDays(1)
        if (timestamp.isAfter(yesterday)) {
            val hoursAgo: Long = ChronoUnit.HOURS.between(now, timestamp).absoluteValue
            return "$hoursAgo h"
        }

        val oneMonthAgo = now.minusMonths(1)
        if (timestamp.isAfter(oneMonthAgo)) {
            val daysAgo = ChronoUnit.DAYS.between(now, timestamp).absoluteValue
            return "$daysAgo d"
        }

        val oneYearAgo = now.minusYears(1)
        if (timestamp.isAfter(oneYearAgo)) {
            val monthsAgo = ChronoUnit.MONTHS.between(now, timestamp).absoluteValue
            return "$monthsAgo m"
        }

        val yearsAgo = ChronoUnit.YEARS.between(now, timestamp)
        return "$yearsAgo y"
    }

    private fun getLogInfoString(context: Context) : String {
        val user: Profile = noteLog.profile
        val targetNote: Note = noteLog.target

        return context.getString(infoTemplate, user.username, targetNote.name)
    }
}