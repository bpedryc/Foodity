package com.haxos.foodity.ui.main.notes

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*

class NotesSearchListener(
    lifecycle: Lifecycle,
    private val searcherViewModel: ISearchingViewModel
) : SearchView.OnQueryTextListener {

    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun onQueryTextChange(newText: String): Boolean {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            delay(500)
            if (newText.isEmpty()) {
                searcherViewModel.resetNoteSearch()
            } else {
                searcherViewModel.searchNotes(newText)
            }
        }
        return false
    }
    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }
}