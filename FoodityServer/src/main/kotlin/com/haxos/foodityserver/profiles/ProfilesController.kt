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

}