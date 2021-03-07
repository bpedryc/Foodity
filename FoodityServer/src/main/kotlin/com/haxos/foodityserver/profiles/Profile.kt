package com.haxos.foodityserver.profiles

import com.haxos.foodityserver.JPAPersistable
import javax.persistence.Entity

@Entity
data class Profile (
    val username: String,
    val firstName: String,
    val lastName: String
) : JPAPersistable<Int>()