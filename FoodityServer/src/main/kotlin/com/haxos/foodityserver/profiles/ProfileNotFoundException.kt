package com.haxos.foodityserver.profiles

class ProfileNotFoundException(
    profileId: Long
) : RuntimeException("Could not find profile $profileId")