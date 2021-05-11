package com.haxos.foodityserver.rest.notes

import com.haxos.foodityserver.jpa.JPAPersistable
import javax.persistence.Entity

@Entity
data class NoteElement (
    val title: String,
    val contents: String
) : JPAPersistable<Long>()
