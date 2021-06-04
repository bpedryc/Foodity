package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.jpa.JPAPersistable

abstract class NoteElement (
    var title: String = "",
    var orderNumber: Int = 0
) : JPAPersistable<Long>() {
    constructor() : this("", 0)
}
