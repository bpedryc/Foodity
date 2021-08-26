package com.haxos.foodityserver.rest.notes.category

data class NotesCategoryRequest (
    val id : Long? = null,
    val name: String,
    val thumbnail: Int,
    val profileId: Long
)