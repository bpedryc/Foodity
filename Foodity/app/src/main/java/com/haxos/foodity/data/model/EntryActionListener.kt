package com.haxos.foodity.data.model

class EntryActionListener (
    val onMoveUp: (ListNoteElementEntryViewModel) -> Unit,
    val onMoveDown: (ListNoteElementEntryViewModel) -> Unit,
    val onDelete: (ListNoteElementEntryViewModel) -> Unit,
)