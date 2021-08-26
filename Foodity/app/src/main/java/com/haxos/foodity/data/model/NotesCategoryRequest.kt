package com.haxos.foodity.data.model

data class NotesCategoryRequest (
    val id : Long? = null,
    val name: String,
    val thumbnail: Int = 0,
    val profileId: Long
)