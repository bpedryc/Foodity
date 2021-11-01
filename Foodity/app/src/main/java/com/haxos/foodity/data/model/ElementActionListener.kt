package com.haxos.foodity.data.model

class ElementActionListener(
        val onMoveUp: (NoteElementViewModel) -> Unit,
        val onMoveDown: (NoteElementViewModel) -> Unit,
        val onDelete: (NoteElementViewModel) -> Unit,
)
