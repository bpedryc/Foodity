package com.haxos.foodityserver.profiles

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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

}