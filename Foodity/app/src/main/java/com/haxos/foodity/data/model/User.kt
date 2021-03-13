package com.haxos.foodity.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User (
    val id: Long,
    val username: String,
    val email: String,
    val password: String
)