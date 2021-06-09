package com.haxos.foodity.data.model

data class NoteRequest (
    val id : Long? = null,
    val name: String,
    val categoryId: Long,
    val thumbnail: Int,
    val description: String,
)
