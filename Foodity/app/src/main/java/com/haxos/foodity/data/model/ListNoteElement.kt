package com.haxos.foodity.data.model

class ListNoteElement (
    id: Long,
    order: Long,
    title: String,
    val entries: MutableList<ListNoteElementEntry>
) : NoteElement(id, order, title)