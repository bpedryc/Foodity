package com.haxos.foodityserver.logs

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.JPAPersistable
import com.haxos.foodityserver.profiles.Profile
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity
data class ProfileLog (
    val type: Type,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    val profile: Profile,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    val target: Profile,

    val timestamp: LocalDateTime
) : JPAPersistable<Long>() {
    enum class Type {
        FOLLOWED,
        UNFOLLOWED
    }
}
