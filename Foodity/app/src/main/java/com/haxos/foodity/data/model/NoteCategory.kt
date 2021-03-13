package com.haxos.foodity.data.model

data class NoteCategory (
    val id: Long,
    val name: String,
    val notes: List<Note>
)