package com.haxos.foodity.ui.main.social

import android.content.Context
import com.haxos.foodity.R
import com.haxos.foodity.data.model.Note
import com.haxos.foodity.data.model.NoteLog
import com.haxos.foodity.data.model.NoteLog.Type.*
import com.haxos.foodity.data.model.Profile

class NoteLogTemplate(private val noteLog: NoteLog) : ILogTemplate {
    override val infoTemplate: Int
        get() = when (noteLog.type) {
                CREATE -> R.string.notelog_create
                EDIT -> R.string.notelog_edit
                DELETE -> R.string.notelog_delete
                LIKE -> R.string.notelog_like
                COMMENT -> if (targetingSelf()) R.string.notelog_commentself else R.string.notelog_comment
            }

    private fun targetingSelf() : Boolean {
        if (noteLog.target.id == noteLog.user.id) {
            return true
        }
        return false
    }

    override fun getInContext(context: Context): DisplayableLog {
        val logTimestamp = noteLog.timestamp.toString()
        val logInfo = getLogInfo(context)

        return DisplayableLog(logTimestamp, logInfo)
    }

    private fun getLogInfo(context: Context) : String {
        val user: Profile = noteLog.user
        val targetNote: Note = noteLog.target
        val targetUser: Profile = targetNote.profile

        if (targetingSelf()) {
            return context.getString(infoTemplate, user.username, targetNote.name)
        }
        return context.getString(infoTemplate, user.username, targetNote.name, targetUser.username)
    }
}