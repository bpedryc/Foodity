package com.haxos.foodityserver.users

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.net.URI

@RestController
class UsersController (
    val service: UserService,
    val repository: UserRepository
){
    @RequestMapping("/users", method = [RequestMethod.GET])
    fun getAll(): MutableList<User> {
        return repository.findAll()
    }

    @RequestMapping(value = ["/users"], params = ["email"], method = [RequestMethod.GET])
    fun getByEmail(@RequestParam email: String): User {
        return repository.findByEmail(email)
    }

    @RequestMapping(value = ["/users"], params = ["username"], method = [RequestMethod.GET])
    fun getByUsername(@RequestParam username: String): User {
        return repository.findByUsername(username)
    }

    @RequestMapping(value = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody user: UserRequest): ResponseEntity<URI>{
        val response = service.createUser(user)
        if (response.status != 201) {
            throw RuntimeException("Error creating user")
        }
        return ResponseEntity.created(response.location).build()
    }
}