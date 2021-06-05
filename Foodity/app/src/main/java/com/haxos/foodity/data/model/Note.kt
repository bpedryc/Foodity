package com.haxos.foodity.data.model

data class Note (
    val id: Long,
    val name: String,
    val categoryId: Long,
    val thumbnail: Int,
    val description: String,
    var elements: List<NoteElement>
)