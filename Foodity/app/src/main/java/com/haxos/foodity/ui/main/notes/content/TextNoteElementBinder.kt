package com.haxos.foodity.ui.main.notes.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.haxos.foodity.R
import com.haxos.foodity.data.model.TextNoteElement

class TextNoteElementBinder(
    private val textNoteElement: TextNoteElement
) : NoteElementBinder() {

    override fun createViewHolder(parent: ViewGroup): NoteElementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_noteelements, parent, false)
        return TextViewHolder(view)
    }

    inner class TextViewHolder(view: View) : NoteElementViewHolder(view) {
        private val contents: TextView = view.findViewById(R.id.noteelement_text_contents)

        override fun bind() {
            title.text = textNoteElement.title
            contents.text = textNoteElement.contents
        }
    }

}
