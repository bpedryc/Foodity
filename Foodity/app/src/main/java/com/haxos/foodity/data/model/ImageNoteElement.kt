package com.haxos.foodity.data.model

class ImageNoteElement (
        id: Long? = null,
        orderNumber: Int,
        title: String,
        var sourcePath: String
) : NoteElement(id, orderNumber, title) {

    override fun equals(other: Any?): Boolean {
        if (!super.equals(other) || other !is ImageNoteElement) {
            return false
        }
        if (sourcePath == other.sourcePath) {
            return true
        }
        return false
    }
}