package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.jpa.JPAPersistable
import javax.persistence.Entity

@Entity
data class ListNoteElementEntry (
    var orderNumber: Int,
    var contents: String

) : JPAPersistable<Long>()
