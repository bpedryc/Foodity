package com.haxos.foodity.data.model

import java.time.LocalDateTime

class ProfileLog (
    val type: Type,
    val user: Profile,
    val target: Profile,
    val timestamp: LocalDateTime
) {
    enum class Type {
        FOLLOWED,
        UNFOLLOWED
    }
}