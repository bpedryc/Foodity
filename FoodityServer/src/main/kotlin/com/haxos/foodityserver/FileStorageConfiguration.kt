package com.haxos.foodityserver

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "file")
data class FileStorageConfiguration (
    val uploadDir: String
)