package com.haxos.foodity.data.model

import androidx.lifecycle.MutableLiveData
import com.haxos.foodity.BR
import com.haxos.foodity.R
import com.haxos.foodity.ui.main.notes.content.RecyclerItem

class ListNoteElementViewModel (
        val listElement: ListNoteElement,
        private val _noteLiveData: MutableLiveData<MutableList<RecyclerItem>>,
        private val editable: Boolean,
        elementActionListener: ElementActionListener
) : NoteElementViewModel(elementActionListener) {

    private val entryActionListener = EntryActionListener(
            onMoveUp = ::moveUpEntry,
            onMoveDown = ::moveDownEntry,
            onDelete = ::deleteEntry)

    val bindableEntries : MutableList<RecyclerItem> = listElement.entries
        .map { ListNoteElementEntryViewModel(it, entryActionListener) }
        .map { it.toRecyclerItem(editable) }
        .toMutableList()

    override fun toRecyclerItem(editable: Boolean) : RecyclerItem {
        var layout = R.layout.recyclerview_element_list
        if (editable) {
            layout = R.layout.recyclerview_element_list_edit
        }
        return RecyclerItem(
                data = this,
                variableId = BR.viewModel,
                layoutId = layout
        )
    }

    fun addEntry() {
        val newEntry = ListNoteElementEntry(
                orderNumber = listElement.entries.size.toLong(),
                contents = "")
        listElement.entries.add(newEntry)

        val newEntryViewModel = ListNoteElementEntryViewModel(newEntry, entryActionListener)
        val newEntryRecyclerItem = newEntryViewModel.toRecyclerItem(editable)
        bindableEntries.add(newEntryRecyclerItem)
        _noteLiveData.value = _noteLiveData.value
    }

    private fun moveUpEntry(entryViewModel: ListNoteElementEntryViewModel) {
        val index : Int = bindableEntries.indexOfFirst {
            it.data == entryViewModel
        }
        if (index <= 0) {
            return
        }

        val prevEntry = bindableEntries[index - 1]
        bindableEntries[index - 1] = bindableEntries[index]
        bindableEntries[index] = prevEntry
        _noteLiveData.value = _noteLiveData.value
    }

    private fun moveDownEntry(entryViewModel: ListNoteElementEntryViewModel) {
        val index : Int = bindableEntries.indexOfFirst {
            it.data == entryViewModel
        }
        if (index >= bindableEntries.size - 1 || index == -1) {
            return
        }

        val nextEntry = bindableEntries[index + 1]
        bindableEntries[index + 1] = bindableEntries[index]
        bindableEntries[index] = nextEntry
        _noteLiveData.value = _noteLiveData.value
    }

    private fun deleteEntry(entryViewModel: ListNoteElementEntryViewModel) {
        bindableEntries.removeIf {
            it.data == entryViewModel
        }
        _noteLiveData.value = _noteLiveData.value
    }
}