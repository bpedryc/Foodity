package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.JsonBackReference
import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class ImageNoteElement (
    title: String,
    orderNumber: Int,
    val sourcePath: String,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    val note: Note

) : NoteElement(title, orderNumber)

