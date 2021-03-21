package com.haxos.foodity.data.model

data class Note (
        val id: Long,
        val name: String,
        val description: String,
        val elements: List<NoteElement>
)