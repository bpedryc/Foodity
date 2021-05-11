package com.haxos.foodityserver.rest.auth

import org.springframework.http.MediaType
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*

@RestController
class AuthController (
    val authService: AuthService
){
    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun getToken(@RequestBody params: MultiValueMap<String, String>): Token? {
        return authService.getToken(
            params.getFirst("username"),
            params.getFirst("password")
        )
    }

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/refresh-token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE])
    fun refreshToken(@RequestBody params: MultiValueMap<String, String>): Token? {
        return authService.refreshToken(
            params.getFirst("refreshToken")
        )
    }
}