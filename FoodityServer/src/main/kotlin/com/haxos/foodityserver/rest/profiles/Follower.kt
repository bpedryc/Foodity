package com.haxos.foodityserver.rest.profiles

import com.haxos.foodityserver.jpa.JPAPersistable
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Follower (
    @ManyToOne
    @JoinColumn(name = "from_profile_id")
    var from: Profile,

    @ManyToOne
    @JoinColumn(name = "to_profile_id")
    var to: Profile
) : JPAPersistable<Long>() {
}