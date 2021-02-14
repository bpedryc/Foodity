package com.haxos.foodityserver.users

import com.haxos.foodityserver.JPAPersistable
import javax.persistence.Entity

@Entity
data class User (
    val username: String,
    val email: String,
    val password: String
) : JPAPersistable<Int>()