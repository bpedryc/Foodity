package com.haxos.foodityserver.rest.notes.noteelement

import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.Entity

@Entity
class ImageNoteElement (
    title: String?,
    orderNumber: Int?,
    note: Note?,
    var sourcePath: String?

) : NoteElement(title, orderNumber, note)

