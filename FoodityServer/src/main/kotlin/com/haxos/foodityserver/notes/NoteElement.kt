package com.haxos.foodityserver.notes

import com.haxos.foodityserver.JPAPersistable
import javax.persistence.Entity

@Entity
data class NoteElement (
    val title: String,
    val contents: String
) : JPAPersistable<Long>()
