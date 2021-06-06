package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.Entity

@Entity
class TextNoteElement (
    title: String,
    orderNumber: Int,
    note: Note?,
    val contents: String

) : NoteElement(title, orderNumber, note)
