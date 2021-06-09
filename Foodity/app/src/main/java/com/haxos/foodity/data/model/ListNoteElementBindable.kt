package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ListNoteElementBindable(val listNoteElement: ListNoteElement) {

    val bindableEntries = listNoteElement.entries.map {
        it.toRecyclerItem()
    }

    private fun ListNoteElementEntry.toRecyclerItem() =
        RecyclerItem(
            data = this,
            variableId = BR.listEntry,
            layoutId = R.layout.recyclerview_element_list_entry
        )
}
