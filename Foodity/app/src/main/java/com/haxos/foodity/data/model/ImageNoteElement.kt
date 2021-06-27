package com.haxos.foodity.data.model

class ImageNoteElement (
        id: Long? = null,
        orderNumber: Int,
        title: String,
        var sourcePath: String
) : NoteElement(id, orderNumber, title)