package com.haxos.foodityserver.rest.profiles

class ProfileNotFoundException(
    profileId: Long
) : RuntimeException("Could not find profile $profileId")