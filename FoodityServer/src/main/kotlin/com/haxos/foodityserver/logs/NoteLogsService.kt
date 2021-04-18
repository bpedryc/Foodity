package com.haxos.foodityserver.logs

import com.haxos.foodityserver.profiles.Profile
import com.haxos.foodityserver.profiles.ProfileNotFoundException
import com.haxos.foodityserver.profiles.ProfilesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteLogsService (
    val profilesRepository: ProfilesRepository, //TODO: create profileService for getting followers/following
    val noteLogsRepository: INoteLogsRepository
){
    fun getFriendLogOf(profileId: Long): List<NoteLog> {
        val friendLogs = ArrayList<NoteLog>()
        val profile: Profile = profilesRepository.findById(profileId)
            .orElseThrow { ProfileNotFoundException(profileId) }

        val allProfiles : List<Profile> = profilesRepository.findAll()
        allProfiles.forEach {
            if (isFollowedBy(profile, it)) {
                val noteLogs: List<NoteLog> = noteLogsRepository.getByProfileId(it.getId()!!)
                friendLogs.addAll(noteLogs)
            }
        }
        return friendLogs
    }

    private fun isFollowedBy(follower: Profile, profile: Profile) : Boolean =
        profile.followers.any {it == follower}
}
