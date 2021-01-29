package com.example.foodity.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User (
    val id: String,
    val username: String,
    val email: String,
    val password: String
)
