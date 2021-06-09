package com.haxos.foodity.data.model

data class Note (
    val id: Long,
    var name: String,
    val categoryId: Long,
    val thumbnail: Int,
    var description: String,
    var elements: List<NoteElement>
)