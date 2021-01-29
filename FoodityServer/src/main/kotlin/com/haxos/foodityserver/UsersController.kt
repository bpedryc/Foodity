package com.haxos.foodityserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UsersController (
    @Autowired val repository: UserRepository
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

}