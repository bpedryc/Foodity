package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElementEntry

class ListNoteElementRequest (
        val id: Long,
        val title: String,
        val order: Int,
        val entries: List<ListNoteElementEntry>
)