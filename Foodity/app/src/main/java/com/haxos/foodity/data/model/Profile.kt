package com.haxos.foodity.data.model

data class Profile (
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String,
        val following: MutableList<Profile>,
        val followers: MutableList<Profile>
)