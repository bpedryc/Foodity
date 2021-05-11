package com.haxos.foodityserver.rest.logs.note

import com.haxos.foodityserver.rest.profiles.Profile
import com.haxos.foodityserver.rest.profiles.ProfileNotFoundException
import com.haxos.foodityserver.rest.profiles.IProfilesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class NoteLogsService (
    val profilesRepository: IProfilesRepository, //TODO: create profileService for getting followers/following
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
        return friendLogs.sortedByDescending { it.timestamp }
    }

    private fun isFollowedBy(follower: Profile, profile: Profile) : Boolean =
        profile.followers.any {it == follower}
}
