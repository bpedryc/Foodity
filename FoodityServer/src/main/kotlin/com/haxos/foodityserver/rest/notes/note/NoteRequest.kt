package com.haxos.foodityserver.rest.notes.note

import com.haxos.foodityserver.rest.notes.NoteElement

data class NoteRequest (
    val id: Long? = null,
    val name: String,
    val categoryId: Long,
    val thumbnail: Int,
    val description: String,
    val elements: List<NoteElement>
)