package com.haxos.foodity.data.model

class ListNoteElement (
        id: Long,
        orderNumber: Int,
        title: String,
        val entries: MutableList<ListNoteElementEntry>
) : NoteElement(id, orderNumber, title)