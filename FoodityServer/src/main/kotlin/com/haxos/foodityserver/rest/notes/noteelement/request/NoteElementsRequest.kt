package com.haxos.foodityserver.rest.notes.noteelement.request

import com.haxos.foodityserver.rest.notes.noteelement.ImageNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.NoteElement
import com.haxos.foodityserver.rest.notes.noteelement.TextNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.image.ImageNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.list.ListNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.text.TextNoteElementRequest

class NoteElementsRequest (
        noteElements: List<NoteElement>
) {
    constructor() : this(emptyList())

//    val noteElementRequests = noteElements.map { it.toRequest() }

    /*private fun NoteElement.toRequest() : NoteElementRequest {
        return when (this) {
            is TextNoteElement -> TextNoteElementRequest(this.getId()!!, this.title!!, this.orderNumber!!, this.contents!!)
            is ListNoteElement -> ListNoteElementRequest(this.getId()!!, this.title!!, this.orderNumber!!, this.entries!!)
            is ImageNoteElement -> ImageNoteElementRequest(this.getId()!!, this.title!!, this.orderNumber!!, this.sourcePath!!)
            else -> TODO()
        }
    }*/
}