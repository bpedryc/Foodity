package com.haxos.foodityserver.rest.file

class FileStorageException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
