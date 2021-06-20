package com.haxos.foodityserver.rest.file

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class WebFileNotFoundException(
    message: String,
    cause: Throwable? = null)
: RuntimeException(message, cause)
