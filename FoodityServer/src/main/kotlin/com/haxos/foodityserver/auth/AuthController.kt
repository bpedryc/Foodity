package com.haxos.foodityserver.auth

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController (
    val authService: AuthService
){
    @RequestMapping(value = ["/token"], params = ["client_id", "username", "password"], method = [RequestMethod.POST])
    fun getToken(
        @RequestParam("client_id") clientId: String,
        @RequestParam("username") username: String,
        @RequestParam("password") password: String
    ): Token? {
        return authService.getToken(clientId, username, password)
    }
}