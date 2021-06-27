package com.haxos.foodity.data.elementRequest

class ImageNoteElementRequest (
        val id: Long?,
        val title: String,
        val orderNumber: Int,
        val sourcePath: String,
        val noteId: Long? = null
)