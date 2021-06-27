package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.*

@Entity
class ListNoteElement (

    title: String? = null,
    orderNumber: Int? = null,
    note: Note? = null,

    @JsonManagedReference
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "list_element_id")
    @OrderBy("orderNumber")
    var entries: MutableList<ListNoteElementEntry>

) : NoteElement(title, orderNumber, note)
