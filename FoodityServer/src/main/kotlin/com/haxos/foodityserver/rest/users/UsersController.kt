package com.haxos.foodityserver.rest.users

import com.haxos.foodityserver.rest.profiles.IProfilesRepository
import com.haxos.foodityserver.rest.profiles.Profile
import org.springframework.web.bind.annotation.*

@RestController
class UsersController (
    val usersService: UsersService,
    val profileRepository: IProfilesRepository
){

    @GetMapping("/users/{username}/roles")
    fun getRole(@PathVariable("username") username: String): List<String> {
        return usersService.getUserRolesByUsername(username)
    }

    @RequestMapping(value = ["/users"], method = [RequestMethod.POST])
    fun createUser(@RequestBody user: UserRequest): UserRequest {
        val response = usersService.createUser(user)
        if (response.status != 201) {
            throw RuntimeException("Error creating user")
        }

        val userProfile = Profile(user.username, "", "")
        profileRepository.save(userProfile)

        return user
    }

    @GetMapping("/users")
    fun getUsers() : List<User> {
        return usersService.getAllUsers()
    }

    @PutMapping("/users/{username}/email")
    fun sendVerificationEmail(@PathVariable username: String) {
        usersService.sendVerificationEmail(username)
    }
}