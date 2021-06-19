package com.haxos.foodity.data.model

class EntryActionListener (
    val onMoveUp: (ListNoteElementViewModel, ListNoteElementEntryViewModel) -> Unit,
    val onMoveDown: (ListNoteElementViewModel, ListNoteElementEntryViewModel) -> Unit,
    val onDelete: (ListNoteElementViewModel, ListNoteElementEntryViewModel) -> Unit
) {

}