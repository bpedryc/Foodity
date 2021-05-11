package com.haxos.foodityserver.rest.notes.category

data class NotesCategoryRequest (
    val name: String,
    val thumbnail: Int,
    val profileId: Long
)