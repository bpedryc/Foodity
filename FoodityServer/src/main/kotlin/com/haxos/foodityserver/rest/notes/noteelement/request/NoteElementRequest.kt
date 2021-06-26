package com.haxos.foodityserver.rest.notes.noteelement.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.haxos.foodityserver.rest.notes.noteelement.image.ImageNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.list.ListNoteElementRequest
import com.haxos.foodityserver.rest.notes.noteelement.text.TextNoteElementRequest

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes(
        JsonSubTypes.Type(value = TextNoteElementRequest::class, name = "TextNoteElementRequest"),
        JsonSubTypes.Type(value = ListNoteElementRequest::class, name = "ListNoteElementRequest"),
        JsonSubTypes.Type(value = ImageNoteElementRequest::class, name = "ImageNoteElementRequest")
)
abstract class NoteElementRequest (
        val id: Long,
        val title: String,
        val order: Int
) {
        constructor() : this(0, "", 0)
}
