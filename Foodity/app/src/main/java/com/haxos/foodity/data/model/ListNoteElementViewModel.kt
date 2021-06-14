package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ListNoteElementViewModel (
        val listElement: ListNoteElement,
        elementActionListener: ElementActionListener
) : NoteElementViewModel(elementActionListener) {

    override fun toRecyclerItem(editable: Boolean) : RecyclerItem {
        var layout = R.layout.recyclerview_element_list
        if (editable) {
            layout = R.layout.recyclerview_element_list_edit
        }
        return RecyclerItem(
                data = this,//ListNoteElementBindable(listElement, editable),
                variableId = BR.viewModel,
                layoutId = layout
        )
    }
}