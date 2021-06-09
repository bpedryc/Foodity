package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ListNoteElementBindable(val listNoteElement: ListNoteElement, private val editable: Boolean) {

    val bindableEntries : List<RecyclerItem> = listNoteElement.entries.map {
        it.toRecyclerItem()
    }

    private fun ListNoteElementEntry.toRecyclerItem() : RecyclerItem {
        var layout = R.layout.recyclerview_element_list_entry
        if (editable) {
            layout = R.layout.recyclerview_element_list_entry_edit
        }
        return RecyclerItem(
            data = this,
            variableId = BR.listEntry,
            layoutId = layout
        )
    }
}
