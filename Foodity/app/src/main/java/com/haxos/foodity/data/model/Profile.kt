package com.haxos.foodity.data.model

data class Profile (
    var id: Long,
    var username: String,
    var firstName: String,
    var lastName: String,
    var blocked: Boolean = false,
    var followingIds: MutableList<Long> = emptyList<Long>().toMutableList(),
    var followerIds: MutableList<Long> = emptyList<Long>().toMutableList(),
    var description: String = "",
    val avatarSrc: String = ""
)