package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.notes.content.TextNoteElementBinder

class TextNoteElement (
    id: Long,
    order: Long,
    title: String,
    val contents: String
) : NoteElement(id, order, title) {

    override fun getAdapter() = TextNoteElementBinder(this)

}