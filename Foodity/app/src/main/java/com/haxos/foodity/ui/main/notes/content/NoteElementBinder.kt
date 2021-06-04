package com.haxos.foodity.ui.main.notes.content

import android.view.ViewGroup

abstract class NoteElementBinder {
    abstract fun createViewHolder(parent: ViewGroup) : NoteElementViewHolder

}
