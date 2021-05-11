package com.haxos.foodityserver.rest.notes.category

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.profiles.Profile
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class NotesCategory (
    val name: String,
    val thumbnail: Int,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    val profile: Profile

) : JPAPersistable<Long>()
