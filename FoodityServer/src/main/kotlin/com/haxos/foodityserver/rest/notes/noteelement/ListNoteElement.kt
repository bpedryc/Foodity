package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.*

@Entity
class ListNoteElement (

    title: String,
    orderNumber: Int,

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "note_id")
    val entries: List<ListNoteElementEntry>,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_element_id")
    val note: Note

) : NoteElement(title, orderNumber)
