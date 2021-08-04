package com.haxos.foodity.data.model

class TextNoteElement (
        id: Long? = null,
        orderNumber: Int,
        title: String,
        var contents: String
) : NoteElement(id, orderNumber, title) {
    override fun equals(other: Any?): Boolean {
        if (!super.equals(other) || other !is TextNoteElement) {
            return false
        }
        if (contents == other.contents) {
            return true
        }
        return false
    }
}