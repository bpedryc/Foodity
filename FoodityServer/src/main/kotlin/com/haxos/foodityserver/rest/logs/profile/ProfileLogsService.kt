package com.haxos.foodityserver.rest.logs.profile

import com.haxos.foodityserver.rest.profiles.Profile
import com.haxos.foodityserver.rest.profiles.IProfilesRepository
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class ProfileLogsService (
    val profilesRepository: IProfilesRepository,
    val profileLogsRepository: IProfileLogsRepository
){
    fun getFriendLogsOf(profileId: Long): List<ProfileLog> {
        val friendLogs = ArrayList<ProfileLog>()
        val profile: Optional<Profile> = profilesRepository.findById(profileId)
        if (!profile.isEmpty) {
            return friendLogs
        }
        //TODO:
        /*profile.get().following.forEach { followingProfile ->
            val followingProfileId: Long? = followingProfile.getId()
            if (followingProfileId != null) {
                val profileLogs: List<ProfileLog> = profileLogsRepository.getByProfileId(followingProfileId)
                friendLogs.addAll(profileLogs)
            }
        }*/
        return friendLogs
    }
}
