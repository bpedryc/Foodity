package com.haxos.foodity.data.model

class ListNoteElement (
    id: Long,
    order: Long,
    title: String,
    val entries: List<ListNoteElementEntry>
) : NoteElement(id, order, title)