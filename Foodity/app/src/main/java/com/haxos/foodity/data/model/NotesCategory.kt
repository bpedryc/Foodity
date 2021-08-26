package com.haxos.foodity.data.model

data class NotesCategory (
    val id: Long,
    var name: String,
    val thumbnail: Int,
    val notes: List<Note>
)