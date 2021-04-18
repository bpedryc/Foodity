package com.haxos.foodity.ui.main.social

import com.haxos.foodity.retrofit.INoteLogService
import com.haxos.foodity.retrofit.IProfileLogService
import javax.inject.Inject

class UserLogService @Inject constructor(
    val noteLogService: INoteLogService,
    val profileLogService: IProfileLogService
) {
    suspend fun getDisplayableLogs(profileId: Long) : List<ILogTemplate> {
        val displayableLogs = ArrayList<ILogTemplate>()

        val noteLogs = noteLogService.getFollowingLogs(profileId)
        val noteLogsBody = noteLogs.body()
        if (noteLogs.isSuccessful && noteLogsBody != null) {
            noteLogsBody.forEach {
                val displayableLog = ILogTemplate.create(it)
                displayableLogs.add(displayableLog)
            }
        }

        val profileLogs = profileLogService.getFollowingLogs(profileId)
        val profileLogsBody = profileLogs.body()
        if (profileLogs.isSuccessful && profileLogsBody != null) {
            profileLogsBody.forEach {
                val displayableLog = ILogTemplate.create(it)
                displayableLogs.add(displayableLog)
            }
        }

        return displayableLogs
    }
}
