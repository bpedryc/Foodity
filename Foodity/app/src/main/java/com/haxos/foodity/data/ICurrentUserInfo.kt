package com.haxos.foodity.data

import com.haxos.foodity.data.model.User

interface ICurrentUserInfo {
    val user: User?
    val profileId: Long?
    val isLoggedIn: Boolean
    val userRoles: List<String>
}