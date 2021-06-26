package com.haxos.foodity.data.elementRequest

import com.haxos.foodity.data.model.ListNoteElementEntry

class ListNoteElementRequest (
        val id: Long,
        val title: String,
        val orderNumber: Int,
        var entries: List<ListNoteElementEntry>
)