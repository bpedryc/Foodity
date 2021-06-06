package com.haxos.foodity.data.model

class ImageNoteElement (
    id: Long,
    order: Long,
    title: String,
    val sourcePath: String
) : NoteElement(id, order, title)