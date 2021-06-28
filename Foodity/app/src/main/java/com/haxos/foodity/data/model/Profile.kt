package com.haxos.foodity.data.model

data class Profile (
        val id: Long,
        val username: String,
        val firstName: String,
        val lastName: String,
        val followingIds: MutableList<Long> = emptyList<Long>().toMutableList(),
        var followerIds: MutableList<Long> = emptyList<Long>().toMutableList()
)