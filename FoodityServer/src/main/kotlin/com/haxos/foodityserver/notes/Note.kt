package com.haxos.foodityserver.notes

import com.haxos.foodityserver.JPAPersistable
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Note (
    val name: String,
    val thumbnail: Int,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true) val elements: List<NoteElement>
) : JPAPersistable<Long>()