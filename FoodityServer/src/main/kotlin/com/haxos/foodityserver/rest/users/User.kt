package com.haxos.foodityserver.rest.users

import com.haxos.foodityserver.rest.profiles.Profile

class User (
    var id: String,
    var username: String,
    var email: String,
    var password: String,
    var profile: Profile?,
    var roles: List<String>
)
