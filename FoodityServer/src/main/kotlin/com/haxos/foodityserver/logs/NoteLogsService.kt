package com.haxos.foodityserver.logs

import com.haxos.foodityserver.profiles.Profile
import com.haxos.foodityserver.profiles.ProfilesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteLogsService (
    val profilesRepository: ProfilesRepository,
    val noteLogsRepository: INoteLogsRepository
){
    fun getFriendLogOf(profileId: Long): List<NoteLog> {
        val friendLogs = ArrayList<NoteLog>()
        val profile: Optional<Profile> = profilesRepository.findById(profileId)
        if (!profile.isEmpty) {
            return friendLogs
        }
        profile.get().following.forEach { followingProfile ->
            val followingProfileId: Long? = followingProfile.getId()
            if (followingProfileId != null) {
                val noteLogs: List<NoteLog> = noteLogsRepository.getByProfileId(followingProfileId)
                friendLogs.addAll(noteLogs)
            }
        }
        return friendLogs
    }
}
