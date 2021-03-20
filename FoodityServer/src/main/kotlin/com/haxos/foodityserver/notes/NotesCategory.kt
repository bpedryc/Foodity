package com.haxos.foodityserver.notes

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.JPAPersistable
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
data class NotesCategory (
    val name: String,
    val thumbnail: Int,

    @JsonManagedReference
    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
    val notes: List<Note>

) : JPAPersistable<Long>()
