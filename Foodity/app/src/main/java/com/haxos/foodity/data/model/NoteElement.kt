package com.haxos.foodity.data.model

abstract class NoteElement (
        var id: Long,
        var order: Long,
        var title: String
) : IBindableNoteElement