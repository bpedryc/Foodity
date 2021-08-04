package com.haxos.foodity.data.model

class ListNoteElement (
        id: Long? = null,
        orderNumber: Int,
        title: String,
        val entries: MutableList<ListNoteElementEntry>
) : NoteElement(id, orderNumber, title) {
    override fun equals(other: Any?): Boolean {
        if (!super.equals(other) || other !is ListNoteElement) {
            return false
        }
        return true
        if (entries == other.entries) {
            return true
        }
        return false
    }
}