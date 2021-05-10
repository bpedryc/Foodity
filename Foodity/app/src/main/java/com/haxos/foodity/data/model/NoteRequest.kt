package com.haxos.foodity.data.model

data class NoteRequest (
    val name: String,
    val categoryId: Long,
    val thumbnail: Int,
    val description: String,
    val elements: List<NoteElement>
)
