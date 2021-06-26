package com.haxos.foodity.data.elementRequest

import com.haxos.foodity.data.model.*

class NoteElementsRequest (
        noteElements: List<NoteElement>
) {
    /*val noteElementRequests = noteElements.map { it.toRequest() }

    private fun NoteElement.toRequest() : NoteElementRequest {
        return when (this) {
            is TextNoteElement -> TextNoteElementRequest(this.id, this.title, this.orderNumber, this.contents)
            is ListNoteElement -> ListNoteElementRequest(this.id, this.title, this.orderNumber, this.entries)
            is ImageNoteElement -> ImageNoteElementRequest(this.id, this.title, this.orderNumber, this.sourcePath)
            else -> TODO()
        }
    }*/
}