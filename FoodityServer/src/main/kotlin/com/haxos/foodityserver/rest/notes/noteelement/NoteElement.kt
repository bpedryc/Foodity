package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.*
import com.haxos.foodityserver.jpa.JPAPersistable
import com.haxos.foodityserver.rest.notes.note.Note
import javax.persistence.*

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
    JsonSubTypes.Type(value = TextNoteElement::class, name = "TextNoteElement"),
    JsonSubTypes.Type(value = ListNoteElement::class, name = "ListNoteElement"),
    JsonSubTypes.Type(value = ImageNoteElement::class, name = "ImageNoteElement")
)
@MappedSuperclass
abstract class NoteElement (
    var title: String? = "",
    var orderNumber: Int? = 0,

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    var note: Note? = null

) : JPAPersistable<Long>() {
    constructor() : this("", 0, null)
}