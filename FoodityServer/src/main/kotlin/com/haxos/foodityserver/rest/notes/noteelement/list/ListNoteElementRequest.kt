package com.haxos.foodityserver.rest.notes.noteelement.list

import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElementEntry

class ListNoteElementRequest (
        val id: Long?,
        val title: String,
        val orderNumber: Int,
        val entries: List<ListNoteElementEntry>,
        val noteId: Long? = null
)