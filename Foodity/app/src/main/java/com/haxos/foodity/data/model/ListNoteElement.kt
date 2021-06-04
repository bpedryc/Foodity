package com.haxos.foodity.data.model

class ListNoteElement (
    id: Long,
    order: Long,
    title: String,
    entries: List<ListNoteElementEntry>
) : NoteElement(id, order, title) {

    override fun getAdapter() = TODO()

}