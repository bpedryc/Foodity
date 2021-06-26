package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.Entity

@Entity
class TextNoteElement (
    title: String?,
    orderNumber: Int?,
    note: Note?,
    var contents: String?

) : NoteElement(title, orderNumber, note)
