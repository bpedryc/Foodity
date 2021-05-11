package com.haxos.foodityserver.rest.profiles

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ProfileNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ProfileNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun profileNotFoundHandler(ex: ProfileNotFoundException) = ex.message
}