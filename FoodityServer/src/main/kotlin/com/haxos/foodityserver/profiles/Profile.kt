package com.haxos.foodityserver.profiles

import com.haxos.foodityserver.JPAPersistable
import com.haxos.foodityserver.notes.NotesCategory
import javax.persistence.*

@Entity
data class Profile (
    val username: String,
    val firstName: String,
    val lastName: String,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    val notesCategories: List<NotesCategory> = ArrayList()
) : JPAPersistable<Long>()