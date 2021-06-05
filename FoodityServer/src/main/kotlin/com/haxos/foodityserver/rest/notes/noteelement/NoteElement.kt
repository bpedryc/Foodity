package com.haxos.foodityserver.rest.notes.noteelement

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.haxos.foodityserver.jpa.JPAPersistable

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
    JsonSubTypes.Type(value = TextNoteElement::class, name = "TextNoteElement"),
    JsonSubTypes.Type(value = ListNoteElement::class, name = "ListNoteElement"),
    JsonSubTypes.Type(value = ImageNoteElement::class, name = "ImageNoteElement")
)
abstract class NoteElement (
    var title: String = "",
    var orderNumber: Int = 0
) : JPAPersistable<Long>() {
    constructor() : this("", 0)
}
