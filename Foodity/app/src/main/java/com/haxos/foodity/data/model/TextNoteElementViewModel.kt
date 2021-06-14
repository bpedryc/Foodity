package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class TextNoteElementViewModel (
        val textElement: TextNoteElement,
        elementActionListener: ElementActionListener
) : NoteElementViewModel(elementActionListener) {

    override fun toRecyclerItem(editable: Boolean) : RecyclerItem {
        var layout = R.layout.recyclerview_element_text
        if (editable) {
            layout = R.layout.recyclerview_element_text_edit
        }

        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = layout
        )
    }
}