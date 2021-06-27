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
) : NoteElementViewModel(listElement, elementActionListener) {

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
                orderNumber = listElement.entries.size.toLong() + 1,
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

        val movedElementPosition = index.toLong() + 1

        val prevEntryViewModel = prevEntry.data as ListNoteElementEntryViewModel
        prevEntryViewModel.entry.orderNumber = movedElementPosition
        entryViewModel.entry.orderNumber = movedElementPosition - 1

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

        val movedElementPosition = index.toLong() + 1

        val nextEntryViewModel = nextEntry.data as ListNoteElementEntryViewModel
        nextEntryViewModel.entry.orderNumber = movedElementPosition
        entryViewModel.entry.orderNumber = movedElementPosition + 1

        _noteLiveData.value = _noteLiveData.value
    }

    private fun deleteEntry(entryViewModel: ListNoteElementEntryViewModel) {
        listElement.entries.remove(entryViewModel.entry)
        bindableEntries.removeIf {
            it.data == entryViewModel
        }

        var orderNumber : Long = 1
        val entries : List<ListNoteElementEntry> = bindableEntries
                .map {it.data}
                .filterIsInstance<ListNoteElementEntryViewModel>()
                .map {it.entry}
        for (entry in entries) {
            entry.orderNumber = orderNumber++
        }

        _noteLiveData.value = _noteLiveData.value
    }
}