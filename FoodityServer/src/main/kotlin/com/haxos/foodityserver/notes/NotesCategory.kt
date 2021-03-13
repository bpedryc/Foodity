package com.haxos.foodityserver.notes

import com.haxos.foodityserver.JPAPersistable
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
data class NotesCategory (
    val name: String,
    val thumbnail: Int,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    val notes: List<Note>

) : JPAPersistable<Long>()
