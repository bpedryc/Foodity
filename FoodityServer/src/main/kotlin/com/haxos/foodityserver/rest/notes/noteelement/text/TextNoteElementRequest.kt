package com.haxos.foodityserver.rest.notes.noteelement.text

class TextNoteElementRequest (
        val id: Long,
        val title: String,
        val order: Int,
        val contents: String
)