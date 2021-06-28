package com.haxos.foodityserver.rest.profiles

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.category.NotesCategory
import javax.persistence.*

@Entity
data class Profile (
    val username: String,
    val firstName: String,
    val lastName: String,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    val notesCategories: List<NotesCategory> = ArrayList(),

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    val followers: MutableList<Profile> = ArrayList()

) : JPAPersistable<Long>()