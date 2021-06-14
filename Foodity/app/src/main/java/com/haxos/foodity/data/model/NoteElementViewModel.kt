package com.haxos.foodity.data.model

import com.haxos.foodity.ui.main.notes.content.RecyclerItem

abstract class NoteElementViewModel (
        private val elementActionListener: ElementActionListener
) {
    fun onMoveUp() {
        elementActionListener.moveUp(this)
    }

    fun onMoveDown() {
        elementActionListener.moveDown(this)
    }

    fun onDelete() {
        elementActionListener.delete(this)
    }

    abstract fun toRecyclerItem(editable: Boolean): RecyclerItem
}