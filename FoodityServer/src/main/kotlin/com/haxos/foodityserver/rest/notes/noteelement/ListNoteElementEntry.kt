package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.jpa.JPAPersistable
import javax.persistence.Entity

@Entity
data class ListNoteElementEntry (
    val orderNumber: Int,
    val contents: String

) : JPAPersistable<Long>()
