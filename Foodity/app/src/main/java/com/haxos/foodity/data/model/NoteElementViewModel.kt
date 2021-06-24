package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.notes.content.RecyclerItem

abstract class NoteElementViewModel (
        val model: NoteElement,
        private val elementActionListener: ElementActionListener
) {
    fun onMoveUp() {
        elementActionListener.onMoveUp(this)
    }

    fun onMoveDown() {
        elementActionListener.onMoveDown(this)
    }

    fun onDelete() {
        elementActionListener.onDelete(this)
    }

    abstract fun toRecyclerItem(editable: Boolean): RecyclerItem
}