package com.haxos.foodity.data.model

import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ListNoteElementEntryViewModel(
        val entry: ListNoteElementEntry,
        private val entryActionListener: EntryActionListener
) {

    fun toRecyclerItem(editable: Boolean) : RecyclerItem {
        var layout = R.layout.recyclerview_element_list_entry
        if (editable) {
            layout = R.layout.recyclerview_element_list_entry_edit
        }
        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = layout
        )
    }

    val orderNumber = entry.orderNumber
    fun onMoveUp() = entryActionListener.onMoveUp(this)
    fun onMoveDown() = entryActionListener.onMoveDown(this)
    fun onDelete() = entryActionListener.onDelete(this)
}