package com.haxos.foodityserver.profiles

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profiles")
class ProfilesController (
        val repository: ProfilesRepository
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

    class FollowerRequest (
        val profileId: Long,
        val followerId: Long
    )

    @PutMapping("/followers")
    fun saveFollower(@RequestBody request: FollowerRequest) : Profile {
        val profile : Profile = repository.findById(request.profileId)
            .orElseThrow { ProfileNotFoundException(request.profileId) }

        val follower = repository.findById(request.followerId)
            .orElseThrow { ProfileNotFoundException(request.followerId) }

        profile.followers.add(follower)
        return repository.save(profile)
    }

    @DeleteMapping("/followers")
    fun removeFollower(@RequestBody request: FollowerRequest) : Profile {
        val profile : Profile = repository.findById(request.profileId)
            .orElseThrow { ProfileNotFoundException(request.profileId) }

        val follower = repository.findById(request.followerId)
            .orElseThrow { ProfileNotFoundException(request.followerId) }

        profile.followers.remove(follower)
        return repository.save(profile)
    }
}