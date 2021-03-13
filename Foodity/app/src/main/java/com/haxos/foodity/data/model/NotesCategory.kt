package com.haxos.foodity.data.model

data class NotesCategory (
    val id: Long,
    val name: String,
    val notes: List<Note>
)