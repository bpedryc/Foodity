package com.haxos.foodity.data.model

class TextNoteElement (
        id: Long? = null,
        orderNumber: Int,
        title: String,
        var contents: String
) : NoteElement(id, orderNumber, title)