package com.haxos.foodityserver.rest.profiles

import javassist.NotFoundException
import org.hibernate.annotations.NotFound
import org.springframework.web.bind.annotation.*
import javax.ws.rs.BadRequestException

@RestController
@RequestMapping("/profiles")
class ProfilesController (
        val profilesRepository: IProfilesRepository,
        val followersRepository: IFollowersRepository
) {
    @GetMapping
    fun all() : List<Profile> {
        return profilesRepository.findAll()
    }

    @GetMapping(params = ["id"])
    fun findById(id: Long) : Profile {
        return profilesRepository.findById(id).orElse(null)
    }

    @GetMapping(params = ["username"])
    fun findByUsername(username: String) : Profile {
        return profilesRepository.findByUsername(username)
    }

    @PutMapping("/{id}/ban")
    fun toggleBan(@PathVariable("id") profileId: Long) : Boolean {
        val profile = profilesRepository.findById(profileId).orElseThrow {
            NotFoundException("Profile not found")
        }
        val isBlocked : Boolean = profile.blocked ?: false
        profile.blocked = !isBlocked
        profilesRepository.save(profile)
        return true
    }

    @PutMapping
    fun update(@RequestBody profileRequest: Profile) : Profile {
        val profileId = profileRequest.getId() ?: throw BadRequestException("No profile id when attempting update")
        val existingProfile = profilesRepository.findById(profileId)
            .orElseThrow { NotFoundException("Profile with id $profileId doesn't exist") }

        existingProfile.apply {
            firstName = profileRequest.firstName
            lastName = profileRequest.lastName
            description = profileRequest.description
            avatarSrc = profileRequest.avatarSrc
        }
        return profilesRepository.save(existingProfile)
    }

    @DeleteMapping(params = ["id"])
    fun delete(id: Long) : Long {
        profilesRepository.deleteById(id)
        return id
    }

    @PutMapping("/{id}/followers")
    fun saveFollower(
        @PathVariable("id") profileId: Long,
        @RequestParam("followerId") followerId: Long
    ) : Follower {
        val profile : Profile = profilesRepository.findById(profileId)
            .orElseThrow { ProfileNotFoundException(profileId) }

        val followerProfile : Profile = profilesRepository.findById(followerId)
            .orElseThrow { ProfileNotFoundException(followerId) }

        val follower = Follower(followerProfile, profile)

        return followersRepository.save(follower)
    }

    @DeleteMapping("/{id}/followers")
    fun removeFollower(
        @PathVariable("id") profileId: Long,
        @RequestParam("followerId") followerProfileId: Long
    ) {
        val follower = followersRepository.findFromTo(followerProfileId, profileId)
            .orElseThrow { ProfileNotFoundException(followerProfileId) }
        followersRepository.delete(follower)
    }

    @GetMapping("/{id}/followers")
    fun getFollowers(@PathVariable("id") profileId: Long): List<Long> {
        val profile: Profile = profilesRepository.findById(profileId)
            .orElseThrow { NotFoundException("Cannot get followers from non-existing profile") }
        return profile.followers
            .map { it.from.getId()!! }
    }

    @GetMapping("/{id}/following")
    fun getFollowing(@PathVariable("id") profileId: Long): List<Long> {
        val profile: Profile = profilesRepository.findById(profileId)
            .orElseThrow { NotFoundException("Cannot get followers from non-existing profile") }
        return profile.following
            .map { it.to.getId()!! }
    }
}