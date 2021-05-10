package com.haxos.foodityserver.notes

data class NoteRequest (
    val name: String,
    val categoryId: Long,
    val thumbnail: Int,
    val description: String,
    val elements: List<NoteElement>
)