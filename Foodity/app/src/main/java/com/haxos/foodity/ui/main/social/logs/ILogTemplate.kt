package com.haxos.foodity.ui.main.social.logs

import android.content.Context
import com.haxos.foodity.data.model.NoteLog
import com.haxos.foodity.data.model.ProfileLog

interface ILogTemplate {

    val infoTemplate: Int

    fun getInContext(context: Context): DisplayableLog

    companion object {
        fun create(profileLog: ProfileLog) : ILogTemplate {
            return ProfileLogTemplate(profileLog)
        }
        fun create(noteLog: NoteLog) : ILogTemplate {
            return NoteLogTemplate(noteLog)
        }
    }
}
