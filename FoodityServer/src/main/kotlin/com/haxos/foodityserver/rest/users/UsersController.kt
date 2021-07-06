package com.haxos.foodityserver.rest.users

import org.springframework.web.bind.annotation.*

@RestController
class UsersController (
        val service: UsersService,
){

    @GetMapping("/users/{username}/roles")
    fun getRole(@PathVariable("username") username: String): List<String> {
        return service.getUserRolesByUsername(username)
    }

    @RequestMapping(value = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody user: UserRequest): UserRequest {
        val response = service.createUser(user)
        if (response.status != 201) {
            throw RuntimeException("Error creating user")
        }
        return user //TODO: create profile for created user and connect them
        //return ResponseEntity.created(response.location).build()
    }
}