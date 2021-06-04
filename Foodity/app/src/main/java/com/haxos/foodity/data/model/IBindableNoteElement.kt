package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.notes.content.NoteElementBinder

interface IBindableNoteElement {
    fun getAdapter() : NoteElementBinder
}
