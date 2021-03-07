package com.haxos.foodityserver.users

import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@RestController
class UsersController (
        val service: UsersService,
){
    /*@RequestMapping("/users", method = [RequestMethod.GET])
    fun getAll(): MutableList<Profile> {
        return service.findAll()
    }

    @RequestMapping(value = ["/users"], params = ["email"], method = [RequestMethod.GET])
    fun getByEmail(@RequestParam email: String): Profile {
        return service.findByEmail(email)
    }

    @RequestMapping(value = ["/users"], params = ["username"], method = [RequestMethod.GET])
    fun getByUsername(@RequestParam username: String): Profile {
        return service.findByUsername(username)
    }*/

    @RequestMapping(value = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody user: UserRequest): UserRequest {
        val response = service.createUser(user)
        if (response.status != 201) {
            throw RuntimeException("Error creating user")
        }
        return user
        //return ResponseEntity.created(response.location).build()
    }
}