package com.haxos.foodity.data.model

data class User (
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val profile: Profile?,
    val roles: List<String>
)