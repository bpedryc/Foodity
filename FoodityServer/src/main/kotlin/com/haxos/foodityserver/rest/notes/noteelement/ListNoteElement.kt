package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToMany

@Entity
class ListNoteElement (

    title: String?,
    orderNumber: Int?,
    note: Note?,

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "list_element_id")
    var entries: List<ListNoteElementEntry>?

) : NoteElement(title, orderNumber, note)
