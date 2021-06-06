package com.haxos.foodity.data.model

class TextNoteElement (
    id: Long,
    order: Long,
    title: String,
    val contents: String
) : NoteElement(id, order, title)