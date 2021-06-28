package com.haxos.foodityserver.rest.profiles

import javassist.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profiles")
class ProfilesController (
        val repository: IProfilesRepository
) {
    @GetMapping
    fun all() : List<Profile> {
        return repository.findAll()
    }

    @GetMapping(params = ["id"])
    fun findById(id: Long) : Profile {
        return repository.findById(id).orElse(null)
    }

    @GetMapping(params = ["username"])
    fun findByUsername(username: String) : Profile {
        return repository.findByUsername(username)
    }

    @PostMapping
    fun saveOrUpdate(@RequestBody profile: Profile) : Profile {
        return repository.save(profile)
    }

    @DeleteMapping(params = ["id"])
    fun delete(id: Long) : Long {
        repository.deleteById(id)
        return id
    }

    @PutMapping("/{id}/followers")
    fun saveFollower(
        @PathVariable("id") profileId: Long,
        @RequestParam("followerId") followerId: Long
    ) : Profile {
        val profile : Profile = repository.findById(profileId)
            .orElseThrow { ProfileNotFoundException(profileId) }

        val follower = repository.findById(followerId)
            .orElseThrow { ProfileNotFoundException(followerId) }

        profile.followers.add(follower)
        return repository.save(profile)
    }

    @DeleteMapping("/{id}/followers")
    fun removeFollower(
        @PathVariable("id") profileId: Long,
        @RequestParam("followerId") followerId: Long
    ) : Profile {
        val profile : Profile = repository.findById(profileId)
            .orElseThrow { ProfileNotFoundException(profileId) }

        val follower = repository.findById(followerId)
            .orElseThrow { ProfileNotFoundException(followerId) }

        profile.followers.remove(follower)
        return repository.save(profile)
    }

    @GetMapping("/{id}/followers")
    fun getFollowers(@PathVariable("id") profileId: Long): List<Long> {
        val profile: Profile = repository.findById(profileId)
            .orElseThrow { NotFoundException("Cannot get followers from non-existing profile") }
        return profile.followers
            .map { it.getId()!! }
    }
}